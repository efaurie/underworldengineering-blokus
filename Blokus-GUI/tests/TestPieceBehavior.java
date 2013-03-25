package tests;

import static org.junit.Assert.*;
import logic.Piece;

import org.junit.Test;

public class TestPieceBehavior {

	@Test
	public void testRotateClockwise() {
		Piece piece1 = createPentPiece();
		piece1.rotateClockwise();
		Piece piece2 = createRotatedCPentPiece();
		checkSame(piece1, piece2);
	}
	
	@Test
	public void testRotateCounterClockwise() {
		Piece piece1 = createPentPiece();
		piece1.rotateCounterClockwise();
		Piece piece2 = createRotatedCCPentPiece();
		checkSame(piece1, piece2);
	}
	
	@Test
	public void testFlip() {
		Piece piece1 = createPentPiece();
		piece1.mirrorOverHorizontal();
		Piece piece2 = createFlippedPentPiece();
		checkSame(piece1, piece2);
	}
	
	@Test
	public void testTurn() {
		Piece piece1 = createPentPiece();
		piece1.mirrorOverVertical();
		Piece piece2 = createTurnedPentPiece();
		checkSame(piece1, piece2);
	}
	
	private void checkSame(Piece piece1, Piece piece2) {
		int[][] matrix1 = piece1.getMatrix();
		int[][] matrix2 = piece2.getMatrix();
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				assertEquals(matrix1[i][j], matrix2[i][j]);
			}
		}
	}
	
	private Piece createPentPiece() {
		int[][] matrix = new int[3][3];
		int[] line1 = {0, 0, 1};
		int[] line2 = {0, 1, 1};
		int[] line3 = {1, 1, 0};
		for(int j = 0; j < 3; j++) {
			for(int i = 0; i < 3; i++) {
				if(j == 0)
					matrix[i][j] = line1[i];
				else if(j == 1)
					matrix[i][j] = line2[i];
				else
					matrix[i][j] = line3[i];
			}
		}
		//      [ ]
		//   [ ][ ]
		//[ ][ ]
		return new Piece(4, 5, matrix);
	}
	
	private Piece createTurnedPentPiece() {
		int[][] matrix = new int[3][3];
		int[] line1 = {1, 0, 0};
		int[] line2 = {1, 1, 0};
		int[] line3 = {0, 1, 1};
		for(int j = 0; j < 3; j++) {
			for(int i = 0; i < 3; i++) {
				if(j == 0)
					matrix[i][j] = line1[i];
				else if(j == 1)
					matrix[i][j] = line2[i];
				else
					matrix[i][j] = line3[i];
			}
		}
		return new Piece(4, 5, matrix);
	}
	
	private Piece createFlippedPentPiece() {
		int[][] matrix = new int[3][3];
		int[] line1 = {1, 1, 0};
		int[] line2 = {0, 1, 1};
		int[] line3 = {0, 0, 1};
		for(int j = 0; j < 3; j++) {
			for(int i = 0; i < 3; i++) {
				if(j == 0)
					matrix[i][j] = line1[i];
				else if(j == 1)
					matrix[i][j] = line2[i];
				else
					matrix[i][j] = line3[i];
			}
		}
		return new Piece(4, 5, matrix);
	}
	
	private Piece createRotatedCPentPiece() {
		int[][] matrix = new int[3][3];
		int[] line1 = {1, 0, 0};
		int[] line2 = {1, 1, 0};
		int[] line3 = {0, 1, 1};
		for(int j = 0; j < 3; j++) {
			for(int i = 0; i < 3; i++) {
				if(j == 0)
					matrix[i][j] = line1[i];
				else if(j == 1)
					matrix[i][j] = line2[i];
				else
					matrix[i][j] = line3[i];
			}
		}
		return new Piece(4, 5, matrix);
	}
	
	private Piece createRotatedCCPentPiece() {
		int[][] matrix = new int[3][3];
		int[] line1 = {1, 1, 0};
		int[] line2 = {0, 1, 1};
		int[] line3 = {0, 0, 1};
		for(int j = 0; j < 3; j++) {
			for(int i = 0; i < 3; i++) {
				if(j == 0)
					matrix[i][j] = line1[i];
				else if(j == 1)
					matrix[i][j] = line2[i];
				else
					matrix[i][j] = line3[i];
			}
		}
		return new Piece(4, 5, matrix);
	}
}
