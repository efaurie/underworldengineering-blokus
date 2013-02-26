import java.awt.Color;

public class Domino extends Piece {
	
	private final int DIMENSION = 2;
	private final int SCORE_VALUE = -2;
	
	public Domino(Color pieceColor, int[][] matrix) {
		initializeMatrix(DIMENSION, matrix);
		setColor(pieceColor);
	}

}
