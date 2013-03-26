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
	
	public GameController() {
		init();
	}
	
	private void init() {
		Lottery<Color> color = new Lottery<Color>(COLORS);
		pieceFactory = new PieceFactory();
		players = new Player[NUMBER_OF_PLAYERS];
		currentPlayer = 0;
		
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
			System.out.println("Playing Woot!");
		}
	}
	
	public void setPlayerName(int player, String newName) {
		players[player].setName(newName);
	}
	
	public void beginTurn() {
		startTime = System.currentTimeMillis();
	}
	
	public void endTurn() {
		endTime = System.currentTimeMillis();
		players[currentPlayer].addTurnLength(endTime - startTime);
		activePiece = null;
		currentPlayer = (currentPlayer + 1) % NUMBER_OF_PLAYERS;
	}
	
	public boolean placePiece(int pieceCol, int pieceRow, int boardCol, int boardRow) {
		boolean wasPlaced = false;
		wasPlaced = gameBoard.placePiece(currentPlayer + 1, activePiece.getNumSquares(), activePiece.getMatrix(),
				pieceCol, pieceRow, boardCol, boardRow);
		return wasPlaced;
	}
	
	public void endGame() {
		
	}
	
	public Player getPlayer(int playerID) {
		return players[playerID];
	}
	
	public Player getCurrentPlayer() {
		return players[currentPlayer];
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
	
	public int getCornersBlocked(int playerID) {
		return gameBoard.getCornersBlocked(playerID);
	}
	
	public long getPlayerControlTime(int playerID) {
		return players[playerID].getTimeInControlMillis();
	}

}
