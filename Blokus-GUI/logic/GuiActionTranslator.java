package logic;
import gui.GameFrame;
import gui.RegistrationForm;

import java.awt.Color;
import java.awt.Point;


public class GuiActionTranslator {
	
	private GameController game;
	private TimeoutPolicy timePolicy;
	private GameFrame frame;
	private Point pieceCoords;
	private Point boardCoords;
	private int passesInARow = 0;
	
	public GuiActionTranslator(GameController game) {
		this.game = game;
		frame = null;
		timePolicy = new TimeoutPolicy(game, this);
		resetCoords();
	}
	
	public void registerFrame(GameFrame frame) {
		this.frame = frame;
	}
	
	public void multiplayStartGameAction() {
		game.startGame();
	}
	
	public void registerAction() {
		new RegistrationForm(this);
	}
	
	public boolean registerUser(String username, String password, String name) {
		return game.createPlayer(username, password, name);
	}
	
	private Player getPlayerInfo(int playerID) {
		return game.getPlayer(playerID);
	}
	
	public PlayTimer getPlayerTimer() {
		return game.getCurrentPlayer().getTimer();
	}
	
	public String getPlayerColorAsString(int playerID) {
		Color playerColor = getPlayerInfo(playerID).getColor();
		if(playerColor == Color.BLUE)
			return "blue";
		else if(playerColor == Color.RED)
			return "red";
		else if(playerColor == Color.GREEN)
			return "green";
		else
			return "yellow";
	}
	
	public String getPlayerName(int playerID) {
		return getPlayerInfo(playerID).getName();
	}
	
	public int getPlayerScore(int playerID) {
		return getPlayerInfo(playerID).getScore();
	}
	
	public void passAction() {
		endTurnAction();
		passesInARow++;
		if(passesInARow >= 4) {
			endGame();
		}
	}
	
	public void resetTurnAction() {
		resetCoords();
		game.resetTurn();
		frame.updateGameFrame();
	}
	
	public void endTurnAction() {
		resetCoords();
		game.endTurn();
		frame.updateGameFrame();
		game.beginTurn();
	}
	
	public void playerTimeoutAction() {
		timePolicy.timeoutAction();
	}
	
	public void timeoutEndTurnAction() {
		resetCoords();
		frame.updateGameFrame();
	}
	
	public int getCurrentPlayer() {
		return game.getCurrentPlayer().getID();
	}
	
	public boolean loginPlayer(int id, String username, String password) {
		return game.playerLogin(id, username, password);
	}
	
	public void piecePanelClickAction(int pieceID, String color) {
		resetCoords();
		frame.updateOrientationPanel(pieceID, color);
		game.setActivePiece(pieceID);
	}
	
	public void resetPlayers() {
		game.resetPlayers();
	}
	
	public void flipPiece() {
		resetCoords();
		game.flipPiece();
	}
	
	public void turnPiece() {
		resetCoords();
		game.turnPiece();
	}
	
	public void rotatePiece(int angle) {
		resetCoords();
		game.rotatePiece(angle);
	}
	
	public void setKindMode() {
		timePolicy.setKickOnTimeout(false);
	}
	
	public void setCruelMode() {
		timePolicy.setKickOnTimeout(true);
	}
	
	public void pieceCoordsSelected(Point coords) {
		pieceCoords = coords;
	}
	
	public void boardCoordsSelected(Point coords) {
		if(pieceCoords != null) {
			boardCoords = coords;
			int pieceX = (int)pieceCoords.getX();
			int pieceY = (int)pieceCoords.getY();
			int boardX = (int)boardCoords.getX();
			int boardY = (int)boardCoords.getY();
			
			boolean wasPlaced = game.placePiece(pieceX, pieceY, boardX, boardY);
			if(wasPlaced == true) {
				frame.removePieceFromPool(game.getCurrentPlayer().getID());
				game.getCurrentPlayer().removePiece(game.getActivePiece());
				passesInARow = 0;
				endTurnAction();
			}
		}
	}
	public int[][] getBoardMatrix() {
		return game.getBoardMatrix();
	}
	
	private void resetCoords() {
		boardCoords = null;
		pieceCoords = null;
	}
	
	public void endGame() {
		resetCoords();
		passesInARow = 0;
		frame.showScoreScreen();
		game.endGame();
		game = new GameController();
	}
	
	public void scoreScreenDoneAction() {
		frame.endGame();
		game = new GameController();
	}
	
	public ScoreReport getReport() {
		ScoreReport report = new ScoreReport();
		String name;
		String color;
		int score;
		long time;
		String timeString;
		for(int i = 0; i < 4; i++) {
			timeString = "";
			name = getPlayerName(i);
			color = getPlayerColorAsString(i);
			score = getPlayerScore(i);
			time = game.getPlayerControlTime(i);
			int seconds = (int) (time / 1000) % 60 ;
			int minutes = (int) ((time / (1000*60)) % 60);
			timeString = timeString + minutes + " minutes, ";
			timeString = timeString + seconds + " seconds";
			report.setData(i, name, color, score, timeString);
		}
		return report;
	}

}
