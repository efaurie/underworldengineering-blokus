import java.awt.Color;

public class Triomino extends Piece {
	
	private final int DIMENSION = 3;
	private final int SCORE_VALUE = -3;
	
	public Triomino(Color pieceColor, int[][] matrix) {
		initializeMatrix(DIMENSION, matrix);
		setColor(pieceColor);
	}

}