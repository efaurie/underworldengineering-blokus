public class Piece {
	
	private int[][] matrix;
	private int squares;
	private int scoreValue;
	
	public Piece(int squares, int[][] matrix) {
		this.squares = squares;
		this.matrix = matrix;
		scoreValue = (-1) * squares;
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
	
	public void printMatrix() {
		for(int i = 0; i < matrix[0].length; i++) {
			for(int j = 0; j < matrix.length; j++) {
				System.out.print(" " + matrix[j][i] + " ");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	
	public void printPiece() {
		for(int i = 0; i < matrix[0].length; i++) {
			for(int j = 0; j < matrix.length; j++) {
				if(matrix[j][i] == 1)
					System.out.print("[]");
				else
					System.out.print("  ");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
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

}
