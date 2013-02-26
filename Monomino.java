import java.awt.Color;

public class Monomino extends Piece {
	
	private final int DIMENSION = 1;
	private final int SCORE_VALUE = -1;
	
	public Monomino(Color pieceColor, int[][] matrix) {
		initializeMatrix(DIMENSION, matrix);
		setColor(pieceColor);
	}
	
	@Override
	public void rotateClockwise() {
		//do nothing
	}
	
	@Override
	public void rotateCounterClockwise() {
		//do nothing
	}
	

}