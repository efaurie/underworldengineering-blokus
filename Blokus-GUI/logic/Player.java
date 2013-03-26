package logic;
import gui.PieceFactory;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;



public class Player {
	
	private int id;
	private Color controlledColor;
	private String name;
	private ArrayList<Piece> pieces;
	private int numPiecesRemaining;
	private boolean monoLastPiece;
	private long timeInControl;
	
	public Player(int playerID, Color playerColor) {
		id = playerID;
		name = "Player " + (playerID + 1);
		controlledColor = playerColor;
		monoLastPiece = false;
		timeInControl = 0;
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
	
	public String getName() {
		return name;
	}

}
