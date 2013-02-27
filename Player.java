import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;


public class Player {
	
	private int id;
	private Color controlledColor;
	private ArrayList<Piece> pieces;
	private int numPiecesRemaining;
	private boolean monoLastPiece;
	private long timeInControl;
	//private int enemyCornersBlocked;
	
	public Player(int playerID, Color playerColor) {
		id = playerID;
		controlledColor = playerColor;
		monoLastPiece = false;
		timeInControl = 0;
		//enemyCornersBlocked = 0;
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
	
	public Piece getPiece(int index) {
		return pieces.get(index);
	}
	
	public void removePiece(Piece piece) {
		boolean removed = pieces.remove(piece);
		if(removed) 
			numPiecesRemaining--;
	}
	
	public int getNumPiecesRemaining() {
		return numPiecesRemaining;
	}

}
