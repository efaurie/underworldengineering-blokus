package logic;
public class Piece {
	
	private int[][] matrix;
	private int[][] standardOrientationMatrix;
	private int squares;
	private int scoreValue;
	private int id;
	
	public Piece(int id, int squares, int[][] matrix) {
		this.id = id;
		this.squares = squares;
		this.standardOrientationMatrix = matrix;
		scoreValue = (-1) * squares;
		reset();
	}
	
	public void rotateClockwise() {
		transposeMatrix();
		for(int i = 0; i < matrix.length / 2; i++) {
			swapColumns(i, (matrix.length - 1) - i);
		}
	}
	
	public void rotateCounterClockwise() {
		transposeMatrix();
		for(int i = 0; i < matrix[0].length / 2; i++) {
			swapRows(i, (matrix[0].length - 1) - i);
		}
	}
	
	public void mirrorOverVertical() {
		for(int i = 0; i < matrix.length / 2; i++) {
			swapColumns(i, (matrix.length - 1) - i);
		}
	}
	
	public void mirrorOverHorizontal() {
		for(int i = 0; i < matrix[0].length / 2; i++) {
			swapRows(i, (matrix[0].length - 1) - i);
		}
	}
	
	private void transposeMatrix() {
		int[][] transposedMatrix = new int[matrix[0].length][matrix.length];
		for(int j = 0; j < matrix[0].length; j++) {
			for(int i = 0; i < matrix.length; i++) {
				transposedMatrix[j][i] = matrix[i][j];
			}
		}
		matrix = transposedMatrix;
	}
	
	private void swapColumns(int column1, int column2) {
		int temp;
		for(int i = 0; i < matrix[0].length; i++) {
			temp = matrix[column1][i];
			matrix[column1][i] = matrix[column2][i];
			matrix[column2][i] = temp;
		}
	}
	
	private void swapRows(int row1, int row2) {
		int temp;
		for(int i = 0; i < matrix.length; i++) {
			temp = matrix[i][row1];
			matrix[i][row1] = matrix[i][row2];
			matrix[i][row2] = temp;
		}
	}
	
	public int getScoreValue() {
		return scoreValue;
	}
	
	public int[][] getMatrix() {
		return matrix;
	}
	
	public int getNumSquares() {
		return squares;
	}
	
	public int getID() {
		return id;
	}
	
	public void reset() {
		matrix = standardOrientationMatrix;
	}

}
