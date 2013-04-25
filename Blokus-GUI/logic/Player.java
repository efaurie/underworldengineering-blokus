package logic;
import gui.PieceFactory;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

public class Player {
	
	private static final int DEFAULT_TIMEOUT = 60;
	private static final int DEFAULT_PLAYER_RANK = 1;
	
	private int id;
	private int controllingID;
	private Color controlledColor;
	private PlayTimer timer;
	private ArrayList<Piece> pieces;
	private int numPiecesRemaining;
	private boolean monoLastPiece;
	private long timeInControl;
	
	private String username;
	private String password;
	private String name;
	private int playerRank;
	private int globalScore;
	
	public Player(int playerID, Color playerColor) {
		id = playerID;
		initDBdata();
		controllingID = playerID;
		playerRank = DEFAULT_PLAYER_RANK;
		timer = new PlayTimer(DEFAULT_TIMEOUT);
		name = "Player " + (playerID + 1);
		controlledColor = playerColor;
		monoLastPiece = false;
		timeInControl = 0;
	}
	
	private void initDBdata() {
		username = null;
		password = null;
		name = "Player " + (id + 1);
		playerRank = 1;
		globalScore = 0;
	}
	
	public void initPieces(PieceFactory factory) {
		pieces = factory.generatePieces();
		numPiecesRemaining = pieces.size();
	}
	
	public int getID() {
		return id;
	}
	
	public Color getColor() {
		return controlledColor;
	}
	
	public int getScore() {
		if(monoLastPiece && pieces.size() == 0)
			return 15;
		else if(pieces.size() == 0)
			return 10;
		else {
			int score = 0;
			for(Piece current : pieces) {
				score += current.getScoreValue();
			}
			return score;
		}
	}
	
	public void addTurnLength(long turnLength) {
		timeInControl += turnLength;
	}
	
	public void startTimer() {
		timer.start();
	}
	
	public void stopTimer() {
		timer.stop();
	}
	
	public PlayTimer getTimer() {
		return timer;
	}
	
	public void setTimeout(int timeout) {
		timer.setTimeout(timeout);
	}
	
	public long getTimeInControlMillis() {
		return timeInControl;
	}
	
	public double getTimeInControlSec() {
		return (timeInControl / 1000.0);
	}
	
	public Iterator<Piece> getPieceIterator() {
		return pieces.iterator();
	}
	
	public Piece getPiece(int pieceID) {
		for(int i = 0; i < pieces.size(); i++) {
			if(pieces.get(i).getID() == pieceID)
				return pieces.get(i);
		}
		return null;
	}
	
	public void removePiece(Piece piece) {
		boolean removed = pieces.remove(piece);
		if(removed) {
			numPiecesRemaining--;
			if(pieces.size() == 0 && piece.getNumSquares() == 1)
				monoLastPiece = true;
		}
	}
	
	public int getNumPiecesRemaining() {
		return numPiecesRemaining;
	}
	
	public void setName(String newName) {
		name = newName;
	}
	
	public void setUserInfo(String username, String password, int score, int rank) {
		this.username = username;
		this.password = password;
		globalScore = score;
		playerRank = rank;
	}
	
	public int getGlobalScore() {
		return globalScore;
	}
	
	public int getRank() {
		return playerRank;
	}
	
	public String username() {
		return username;
	}
	
	public String password() {
		return password;
	}
	
	public void setControllingID(int newPlayerInControl) {
		controllingID = newPlayerInControl;
	}
	
	
	public int getControllingID() {
		return controllingID;
	}
	
	public String getName() {
		return name;
	}

}
