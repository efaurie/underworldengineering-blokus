package logic;
import gui.PieceFactory;

import java.awt.Color;



public class GameController {
	
	private final int NUMBER_OF_PLAYERS = 4;
	private final Color[] COLORS = {Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE};
	private static PieceFactory pieceFactory;
	
	private boolean playing;
	private Board gameBoard;
	private Player[] players;
	private long startTime, endTime;
	
	private int currentPlayer;
	
	private Piece activePiece;
	private int playersRemaining;
	
	private DatabaseInterface db;
	
	public GameController() {
		init();
	}
	
	private void init() {
		Lottery<Color> color = new Lottery<Color>(COLORS);
		pieceFactory = new PieceFactory();
		players = new Player[NUMBER_OF_PLAYERS];
		playersRemaining = NUMBER_OF_PLAYERS;
		currentPlayer = 0;
		
		db = new DatabaseInterface();
		db.connect();
		
		playing = false;
		gameBoard = new Board();
		for(int i = 0; i < NUMBER_OF_PLAYERS; i++) {
			players[i] = new Player(i, color.getNext());
			players[i].initPieces(pieceFactory);
		}
	}
	
	public void startGame() {
		if(playing == false) {
			playing = true;
		}
		beginTurn();
	}
	
	public void setPlayerName(int player, String newName) {
		players[player].setName(newName);
	}
	
	public void beginTurn() {
		startTime = System.currentTimeMillis();
		players[currentPlayer].startTimer();
	}
	
	public void endTurn() {
		players[currentPlayer].stopTimer();
		endTime = System.currentTimeMillis();
		players[currentPlayer].addTurnLength(endTime - startTime);
		activePiece = null;
		currentPlayer = (currentPlayer + 1) % NUMBER_OF_PLAYERS;
	}
	
	public void resetTurn() {
		startTime = System.currentTimeMillis();
		players[currentPlayer].startTimer();
		activePiece = null;
	}
	
	public boolean placePiece(int pieceCol, int pieceRow, int boardCol, int boardRow) {
		boolean wasPlaced = false;
		wasPlaced = gameBoard.placePiece(currentPlayer + 1, activePiece.getNumSquares(), activePiece.getMatrix(),
				pieceCol, pieceRow, boardCol, boardRow);
		return wasPlaced;
	}
	
	public void endGame() {
		updateUserStats();
		db.disconnect();
	}
	
	public Player getPlayer(int playerID) {
		return players[playerID];
	}
	
	public Player getCurrentPlayer() {
		return players[currentPlayer];
	}
	
	public void playerKicked(int replacement) {
		
		if(players[replacement].getControllingID() == replacement) {
			if(players[currentPlayer].getControllingID() == currentPlayer) {
				players[currentPlayer].setControllingID(replacement);
				players[currentPlayer].getTimer().setTimeout(players[replacement].getTimer().getTimeout());
				System.out.println("Player " + (replacement+1) + " is now in control of Player " + (currentPlayer + 1));
			} else {
				int parentID = players[currentPlayer].getControllingID();
				players[parentID].setControllingID(replacement);
				players[parentID].getTimer().setTimeout(players[replacement].getTimer().getTimeout());
				players[currentPlayer].setControllingID(replacement);
				players[currentPlayer].getTimer().setTimeout(players[replacement].getTimer().getTimeout());
				System.out.println("Player " + (replacement+1) + " is now in control of Player " + 
						(currentPlayer + 1) + " and " + (parentID + 1));
			}
		} else {
			int trueReplacement = players[replacement].getControllingID();
			if(players[currentPlayer].getControllingID() == currentPlayer) {
				players[currentPlayer].setControllingID(trueReplacement);
				players[currentPlayer].getTimer().setTimeout(players[trueReplacement].getTimer().getTimeout());
				System.out.println("Player " + (trueReplacement+1) + " is now in control of Player " +
						(currentPlayer + 1) + " and " + (replacement+1));
			} else {
				int parentID = players[currentPlayer].getControllingID();
				players[parentID].setControllingID(trueReplacement);
				players[parentID].getTimer().setTimeout(players[trueReplacement].getTimer().getTimeout());
				players[currentPlayer].setControllingID(trueReplacement);
				players[currentPlayer].getTimer().setTimeout(players[trueReplacement].getTimer().getTimeout());
				System.out.println("Player " + (trueReplacement+1) + " is now in control of Player " + 
						(currentPlayer + 1) + " and " + (parentID + 1) + " and " + (replacement + 1));
			}
		}
		
		playersRemaining--;
	}
	
	public int getPlayersRemaining() {
		return playersRemaining;
	}
	
	public void setActivePiece(int pieceID) {
		if(activePiece == null)
			activePiece = players[currentPlayer].getPiece(pieceID);
		else {
			activePiece.reset();
			activePiece = players[currentPlayer].getPiece(pieceID);
		}
	}
	
	public Piece getActivePiece() {
		return activePiece;
	}
	
	public void flipPiece() {
		activePiece.mirrorOverHorizontal();
	}
	
	public void turnPiece() {
		activePiece.mirrorOverVertical();
	}
	
	public void rotatePiece(int angle) {
		if(angle < 0)
			activePiece.rotateCounterClockwise();
		else
			activePiece.rotateClockwise();
	}
	
	public int[][] getBoardMatrix() {
		return gameBoard.getMatrix();
	}
	
	public long getPlayerControlTime(int playerID) {
		return players[playerID].getTimeInControlMillis();
	}
	
	public boolean playerLogin(int id, String username, String password) {
		if(db.loginCorrect(username, password)) {
			players[id].setName(db.getPlayerName(username, password));
			int score = db.getPlayerScore(username, password);
			int rank = db.getPlayerRank(username, password);
			players[id].setUserInfo(username, password, score, rank);
			return true;
		} else
			return false;
	}
	
	public boolean createPlayer(String username, String password, String name) {
		return db.createUser(username, password, name);
	}
	
	private void updateUserStats() {
		StatPolicy policy = new StatPolicy();
		int[] scores = policy.calculateScores(players);
		
		int controllingID;
		for(int i = 0; i < players.length; i++) {
			controllingID = players[i].getControllingID();
			if(!players[controllingID].isGuest()) {
				int globalScore = players[controllingID].getGlobalScore() + scores[i];
				players[controllingID].setGlobalScore(globalScore);
				players[controllingID].setRank(policy.getRank(globalScore));
			}
		}
		for(int i = 0; i < players.length; i++) {
			if(!players[i].isGuest()) {
				String username = players[i].getUsername();
				String password = players[i].getPassword();
				int newScore = players[i].getGlobalScore();
				int newRank = players[i].getRank();
				db.updatePlayerEntry(username, password, newScore, newRank);
			}
		}
	}
}
