import java.awt.Color;

public class Pentomino extends Piece {
	
	private final int DIMENSION = 5;
	private final int SCORE_VALUE = -5;
	
	public Pentomino(Color pieceColor, int[][] matrix) {
		initializeMatrix(DIMENSION, matrix);
		setColor(pieceColor);
	}

}
