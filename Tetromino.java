import java.awt.Color;

public class Tetromino extends Piece {
	
	private final int DIMENSION = 4;
	private final int SCORE_VALUE = -4;
	
	public Tetromino(Color pieceColor, int[][] matrix) {
		initializeMatrix(DIMENSION, matrix);
		setColor(pieceColor);
	}

}