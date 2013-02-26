import java.awt.Color;

public abstract class Piece {
	
	int[][] matrixRepresentation;
	int dimension;
	Color pieceColor;
	
	protected void initializeMatrix(int dimension, int[][] matrix) {
		this.dimension = dimension;
		matrixRepresentation = matrix;
	}
	
	protected void setColor(Color pieceColor) {
		this.pieceColor = pieceColor;
	}
	
	public void rotateClockwise() {
		transposeMatrix();
		for(int i = 0; i < dimension / 2; i++) {
			swapColumns(i, (dimension - 1) - i);
		}
	}
	
	public void rotateCounterClockwise() {
		transposeMatrix();
		for(int i = 0; i < dimension / 2; i++) {
			swapRows(i, (dimension - 1) - i);
		}
	}
	
	private void transposeMatrix() {
		int[][] transposedMatrix = new int[dimension][dimension];
		for(int j = 0; j < dimension; j++) {
			for(int i = 0; i < dimension; i++) {
				transposedMatrix[j][i] = matrixRepresentation[i][j];
			}
		}
		matrixRepresentation = transposedMatrix;
		printMatrix();
	}
	
	private void swapColumns(int column1, int column2) {
		int temp;
		for(int i = 0; i < dimension; i++) {
			temp = matrixRepresentation[i][column1];
			matrixRepresentation[i][column1] = matrixRepresentation[i][column2];
			matrixRepresentation[i][column2] = temp;
		}
	}
	
	private void swapRows(int row1, int row2) {
		int temp;
		for(int i = 0; i < dimension; i++) {
			temp = matrixRepresentation[row1][i];
			matrixRepresentation[row1][i] = matrixRepresentation[row2][i];
			matrixRepresentation[row2][i] = temp;
		}
	}
	
	public void printMatrix() {
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				System.out.print(" " + matrixRepresentation[i][j] + " ");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}

}
