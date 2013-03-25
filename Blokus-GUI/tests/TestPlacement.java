package tests;
import static org.junit.Assert.*;
import logic.Board;
import logic.Piece;

import org.junit.Test;


public class TestPlacement {
	
	@Test
	public void testMonoPlacedOffBoard() {
		Board gameBoard = new Board();
		Piece testPiece = createMonoPiece();
		int[][] pieceMatrix = testPiece.getMatrix();
		//test out of bounds to the top
		assertFalse(gameBoard.placePiece(1, 1, pieceMatrix, 0, 0, 0, -1));
		//test out of bounds to the left
		assertFalse(gameBoard.placePiece(1, 1, pieceMatrix, 0, 0, -1, 0));
		//test out of bounds to the bottom
		assertFalse(gameBoard.placePiece(1, 1, pieceMatrix, 0, 0, 0, 20));
		//test out of bounds to the right
		assertFalse(gameBoard.placePiece(1, 1, pieceMatrix, 0, 0, 20, 0));
	}
	
	@Test
	public void testDomPlacedOffBoard() {
		Board gameBoard = new Board();
		Piece testPiece = createDomPiece();
		int[][] pieceMatrix = testPiece.getMatrix();
		//test out of bounds to the top
		assertFalse(gameBoard.placePiece(1, 2, pieceMatrix, 0, 0, 0, -1));
		//test out of bounds to the left
		assertFalse(gameBoard.placePiece(1, 2, pieceMatrix, 0, 0, -1, 0));
		//test out of bounds to the bottom
		assertFalse(gameBoard.placePiece(1, 2, pieceMatrix, 0, 0, 0, 20));
		//test out of bounds to the right
		assertFalse(gameBoard.placePiece(1, 2, pieceMatrix, 0, 0, 20, 0));
	}
	
	@Test
	public void testTriPlacedOffBoard() {
		Board gameBoard = new Board();
		Piece testPiece = createTriPiece();
		int[][] pieceMatrix = testPiece.getMatrix();
		//test out of bounds to the top
		assertFalse(gameBoard.placePiece(1, 3, pieceMatrix, 0, 0, 0, -1));
		//test out of bounds to the left
		assertFalse(gameBoard.placePiece(1, 3, pieceMatrix, 0, 0, -1, 0));
		//test out of bounds to the bottom
		assertFalse(gameBoard.placePiece(1, 3, pieceMatrix, 0, 0, 0, 20));
		//test out of bounds to the right
		assertFalse(gameBoard.placePiece(1, 3, pieceMatrix, 0, 0, 20, 0));
	}
	
	@Test
	public void testTetrPlacedOffBoard() {
		Board gameBoard = new Board();
		Piece testPiece = createTetPiece();
		int[][] pieceMatrix = testPiece.getMatrix();
		//test out of bounds to the top
		assertFalse(gameBoard.placePiece(1, 4, pieceMatrix, 0, 0, 0, -1));
		//test out of bounds to the left
		assertFalse(gameBoard.placePiece(1, 4, pieceMatrix, 0, 0, -1, 0));
		//test out of bounds to the bottom
		assertFalse(gameBoard.placePiece(1, 4, pieceMatrix, 0, 0, 0, 20));
		//test out of bounds to the right
		assertFalse(gameBoard.placePiece(1, 4, pieceMatrix, 0, 0, 20, 0));
	}
	
	@Test
	public void testPentPlacedOffBoard() {
		Board gameBoard = new Board();
		Piece testPiece = createPentPiece();
		int[][] pieceMatrix = testPiece.getMatrix();
		//test out of bounds to the top
		assertFalse(gameBoard.placePiece(1, 5, pieceMatrix, 0, 0, 0, -1));
		//test out of bounds to the left
		assertFalse(gameBoard.placePiece(1, 5, pieceMatrix, 0, 0, -1, 0));
		//test out of bounds to the bottom
		assertFalse(gameBoard.placePiece(1, 5, pieceMatrix, 0, 0, 0, 20));
		//test out of bounds to the right
		assertFalse(gameBoard.placePiece(1, 5, pieceMatrix, 0, 0, 20, 0));
	}
	
	@Test
	public void testMonoPlacedInCornerOnFirstTurn() {
		Board gameBoard = new Board();
		Piece testPiece = createMonoPiece();
		int[][] pieceMatrix = testPiece.getMatrix();
		//test accepted placement in all corner spaces
		assertTrue(gameBoard.placePiece(1, 1, pieceMatrix, 0, 0, 0, 0));
		assertTrue(gameBoard.placePiece(2, 1, pieceMatrix, 0, 0, 19, 0));
		assertTrue(gameBoard.placePiece(3, 1, pieceMatrix, 0, 0, 19, 19));
		assertTrue(gameBoard.placePiece(4, 1, pieceMatrix, 0, 0, 0, 19));
	}
	
	@Test
	public void testDomPlacedInCornerOnFirstTurn() {
		Board gameBoard = new Board();
		Piece testPiece = createDomPiece();
		int[][] pieceMatrix = testPiece.getMatrix();
		//test accepted placement in all corner spaces
		assertTrue(gameBoard.placePiece(1, 2, pieceMatrix, 0, 0, 0, 0));
		assertTrue(gameBoard.placePiece(2, 2, pieceMatrix, 1, 0, 19, 0));
		assertTrue(gameBoard.placePiece(3, 2, pieceMatrix, 1, 0, 19, 19));
		assertTrue(gameBoard.placePiece(4, 2, pieceMatrix, 0, 0, 0, 19));
	}
	
	@Test
	public void testTriPlacedInCornerOnFirstTurn() {
		Board gameBoard = new Board();
		Piece testPiece = createTriPiece();
		int[][] pieceMatrix = testPiece.getMatrix();
		//test accepted placement in all corner spaces
		assertTrue(gameBoard.placePiece(1, 3, pieceMatrix, 0, 0, 0, 0));
		assertTrue(gameBoard.placePiece(2, 3, pieceMatrix, 1, 0, 19, 0));
		assertTrue(gameBoard.placePiece(4, 3, pieceMatrix, 0, 1, 0, 19));
		testPiece.rotateClockwise();
		pieceMatrix = testPiece.getMatrix();
		assertTrue(gameBoard.placePiece(3, 3, pieceMatrix, 1, 1, 19, 19));
	}
	
	@Test
	public void testTetPlacedInCornerOnFirstTurn() {
		Board gameBoard = new Board();
		Piece testPiece = createTetPiece();
		int[][] pieceMatrix = testPiece.getMatrix();
		//test accepted placement in all corner spaces
		assertTrue(gameBoard.placePiece(1, 4, pieceMatrix, 0, 1, 0, 19));
		assertTrue(gameBoard.placePiece(2, 4, pieceMatrix, 2, 0, 19, 0));
		testPiece.rotateClockwise();
		pieceMatrix = testPiece.getMatrix();
		assertTrue(gameBoard.placePiece(4, 4, pieceMatrix, 0, 0, 0, 0));
		assertTrue(gameBoard.placePiece(3, 4, pieceMatrix, 1, 2, 19, 19));
	}
	
	@Test
	public void testPentPlacedInCornerOnFirstTurn() {
		Board gameBoard = new Board();
		Piece testPiece = createPentPiece();
		int[][] pieceMatrix = testPiece.getMatrix();
		//test accepted placement in all corner spaces
		assertTrue(gameBoard.placePiece(1, 5, pieceMatrix, 0, 2, 0, 19));
		assertTrue(gameBoard.placePiece(2, 5, pieceMatrix, 2, 0, 19, 0));
		testPiece.rotateClockwise();
		pieceMatrix = testPiece.getMatrix();
		assertTrue(gameBoard.placePiece(4, 5, pieceMatrix, 0, 0, 0, 0));
		assertTrue(gameBoard.placePiece(3, 5, pieceMatrix, 2, 2, 19, 19));
	}
	
	@Test
	public void testMonoPlacedInCenterOnFirstTurn() {
		Board gameBoard = new Board();
		Piece testPiece = createMonoPiece();
		int[][] pieceMatrix = testPiece.getMatrix();
		assertFalse(gameBoard.placePiece(1, 1, pieceMatrix, 0, 0, 10, 10));
	}
	
	@Test
	public void testDomPlacedInCenterOnFirstTurn() {
		Board gameBoard = new Board();
		Piece testPiece = createDomPiece();
		int[][] pieceMatrix = testPiece.getMatrix();
		assertFalse(gameBoard.placePiece(1, 2, pieceMatrix, 0, 0, 10, 10));
	}
	
	@Test
	public void testTriPlacedInCenterOnFirstTurn() {
		Board gameBoard = new Board();
		Piece testPiece = createTriPiece();
		int[][] pieceMatrix = testPiece.getMatrix();
		assertFalse(gameBoard.placePiece(1, 3, pieceMatrix, 0, 0, 10, 10));
	}
	
	@Test
	public void testTetPlacedInCenterOnFirstTurn() {
		Board gameBoard = new Board();
		Piece testPiece = createTetPiece();
		int[][] pieceMatrix = testPiece.getMatrix();
		assertFalse(gameBoard.placePiece(1, 4, pieceMatrix, 0, 0, 10, 10));
	}
	
	@Test
	public void testPentPlacedInCenterOnFirstTurn() {
		Board gameBoard = new Board();
		Piece testPiece = createPentPiece();
		int[][] pieceMatrix = testPiece.getMatrix();
		assertFalse(gameBoard.placePiece(1, 5, pieceMatrix, 0, 0, 10, 10));
	}
	
	@Test
	public void testMonoPlacedOverlappingPiece() {
		Board gameBoard = new Board();
		Piece testPiece = createMonoPiece();
		int[][] pieceMatrix = testPiece.getMatrix();
		gameBoard.placePiece(1, 1, pieceMatrix, 0, 0, 0, 0);
		//Overlapping a players own piece
		assertFalse(gameBoard.placePiece(1, 1, pieceMatrix, 0, 0, 0, 0));
		//Overlapping another players piece
		assertFalse(gameBoard.placePiece(2, 1, pieceMatrix, 0, 0, 0, 0));
	}
	
	@Test
	public void testDomPlacedOverlappingPiece() {
		Board gameBoard = new Board();
		Piece testPiece = createDomPiece();
		int[][] pieceMatrix = testPiece.getMatrix();
		gameBoard.placePiece(1, 2, pieceMatrix, 0, 0, 0, 0);
		//Overlapping a players own piece
		assertFalse(gameBoard.placePiece(1, 2, pieceMatrix, 0, 0, 0, 0));
		//Overlapping another players piece
		assertFalse(gameBoard.placePiece(2, 2, pieceMatrix, 0, 0, 0, 0));
	}
	
	@Test
	public void testTriPlacedOverlappingPiece() {
		Board gameBoard = new Board();
		Piece testPiece = createTriPiece();
		int[][] pieceMatrix = testPiece.getMatrix();
		gameBoard.placePiece(1, 3, pieceMatrix, 0, 0, 0, 0);
		//Overlapping a players own piece
		assertFalse(gameBoard.placePiece(1, 3, pieceMatrix, 0, 0, 0, 0));
		//Overlapping another players piece
		assertFalse(gameBoard.placePiece(2, 3, pieceMatrix, 0, 0, 0, 0));
	}
	
	@Test
	public void testTetPlacedOverlappingPiece() {
		Board gameBoard = new Board();
		Piece testPiece = createTetPiece();
		testPiece.rotateClockwise();
		int[][] pieceMatrix = testPiece.getMatrix();
		gameBoard.placePiece(1, 4, pieceMatrix, 0, 0, 0, 0);
		//Overlapping a players own piece
		assertFalse(gameBoard.placePiece(1, 4, pieceMatrix, 0, 0, 0, 0));
		//Overlapping another players piece
		assertFalse(gameBoard.placePiece(2, 4, pieceMatrix, 0, 0, 0, 0));
	}
	
	@Test
	public void testPentPlacedOverlappingPiece() {
		Board gameBoard = new Board();
		Piece testPiece = createPentPiece();
		testPiece.rotateClockwise();
		int[][] pieceMatrix = testPiece.getMatrix();
		gameBoard.placePiece(1, 5, pieceMatrix, 0, 0, 0, 0);
		//Overlapping a players own piece
		assertFalse(gameBoard.placePiece(1, 5, pieceMatrix, 0, 0, 0, 0));
		//Overlapping another players piece
		assertFalse(gameBoard.placePiece(2, 5, pieceMatrix, 0, 0, 0, 0));
	}
	
	@Test
	public void testMonoPlacedWithEdgeAgainstAnotherPiece() {
		Board gameBoard = new Board();
		Piece testPiece = createMonoPiece();
		int[][] pieceMatrix = testPiece.getMatrix();
		gameBoard.placePiece(1, 1, pieceMatrix, 0, 0, 0, 0);
		//against one's own piece (invalid)
		assertFalse(gameBoard.placePiece(1, 1, pieceMatrix, 0, 0, 1, 0));
	}
	
	@Test
	public void testDomPlacedWithEdgeAgainstAnotherPiece() {
		Board gameBoard = new Board();
		Piece testPiece = createDomPiece();
		int[][] pieceMatrix = testPiece.getMatrix();
		gameBoard.placePiece(1, 2, pieceMatrix, 0, 0, 0, 0);
		//against one's own piece (invalid)
		assertFalse(gameBoard.placePiece(1, 2, pieceMatrix, 0, 0, 2, 0));
	}
	
	@Test
	public void testTriPlacedWithEdgeAgainstAnotherPiece() {
		Board gameBoard = new Board();
		Piece testPiece = createTriPiece();
		int[][] pieceMatrix = testPiece.getMatrix();
		gameBoard.placePiece(1, 3, pieceMatrix, 0, 0, 0, 0);
		//against one's own piece (invalid)
		assertFalse(gameBoard.placePiece(1, 3, pieceMatrix, 0, 0, 1, 1));
	}
	
	@Test
	public void testTetPlacedWithEdgeAgainstAnotherPiece() {
		Board gameBoard = new Board();
		Piece testPiece = createTetPiece();
		testPiece.rotateClockwise();
		int[][] pieceMatrix = testPiece.getMatrix();
		gameBoard.placePiece(1, 4, pieceMatrix, 0, 0, 0, 0);
		//against one's own piece (invalid)
		assertFalse(gameBoard.placePiece(1, 4, pieceMatrix, 0, 0, 2, 0));
	}
	
	@Test
	public void testPentPlacedWithEdgeAgainstAnotherPiece() {
		Board gameBoard = new Board();
		Piece testPiece = createPentPiece();
		testPiece.rotateClockwise();
		int[][] pieceMatrix = testPiece.getMatrix();
		gameBoard.placePiece(1, 5, pieceMatrix, 0, 0, 0, 0);
		//against one's own piece (invalid)
		assertFalse(gameBoard.placePiece(1, 5, pieceMatrix, 0, 0, 2, 0));
	}
	
	@Test
	public void testMonoPlacedWithTouchingCorner() {
		Board gameBoard = new Board();
		Piece testPiece = createMonoPiece();
		int[][] pieceMatrix = testPiece.getMatrix();
		gameBoard.placePiece(1, 1, pieceMatrix, 0, 0, 0, 0);
		gameBoard.placePiece(1, 1, pieceMatrix, 0, 0, 19, 0);
		//touching one's own piece's corner (valid)
		assertTrue(gameBoard.placePiece(1, 1, pieceMatrix, 0, 0, 1, 1));
		//touching only another player's piece's corner (invalid)
		assertFalse(gameBoard.placePiece(2, 1, pieceMatrix, 0, 0, 18, 1));
	}
	
	@Test
	public void testDomPlacedWithTouchingCorner() {
		Board gameBoard = new Board();
		Piece testPiece = createDomPiece();
		int[][] pieceMatrix = testPiece.getMatrix();
		gameBoard.placePiece(1, 2, pieceMatrix, 0, 0, 0, 0);
		gameBoard.placePiece(1, 2, pieceMatrix, 0, 1, 19, 0);
		//touching one's own piece's corner (valid)
		assertTrue(gameBoard.placePiece(1, 2, pieceMatrix, 0, 0, 2, 1));
		//touching only another player's piece's corner (invalid)
		assertFalse(gameBoard.placePiece(2, 2, pieceMatrix, 0, 0, 17, 1));
	}
	
	@Test
	public void testTriPlacedWithTouchingCorner() {
		Board gameBoard = new Board();
		Piece testPiece = createTriPiece();
		int[][] pieceMatrix = testPiece.getMatrix();
		gameBoard.placePiece(1, 3, pieceMatrix, 0, 0, 0, 0);
		gameBoard.placePiece(1, 3, pieceMatrix, 0, 1, 19, 0);
		//touching one's own piece's corner (valid)
		assertTrue(gameBoard.placePiece(1, 3, pieceMatrix, 0, 0, 2, 1));
		//touching only another player's piece's corner (invalid)
		assertFalse(gameBoard.placePiece(2, 3, pieceMatrix, 0, 1, 17, 2));
	}
	
	@Test
	public void testTetPlacedWithTouchingCorner() {
		Board gameBoard = new Board();
		Piece testPiece = createTetPiece();
		testPiece.rotateClockwise();
		int[][] pieceMatrix = testPiece.getMatrix();
		gameBoard.placePiece(1, 4, pieceMatrix, 0, 0, 0, 0);
		gameBoard.placePiece(1, 4, pieceMatrix, 1, 3, 19, 19);
		//touching one's own piece's corner (valid)
		assertTrue(gameBoard.placePiece(1, 4, pieceMatrix, 0, 0, 2, 3));
		//touching only another player's piece's corner (invalid)
		assertFalse(gameBoard.placePiece(2, 4, pieceMatrix, 1, 3, 17, 16));
	}
	
	@Test
	public void testPentPlacedWithTouchingCorner() {
		Board gameBoard = new Board();
		Piece testPiece = createPentPiece();
		testPiece.rotateClockwise();
		int[][] pieceMatrix = testPiece.getMatrix();
		gameBoard.placePiece(1, 5, pieceMatrix, 0, 0, 0, 0);
		gameBoard.placePiece(1, 5, pieceMatrix, 2, 2, 19, 19);
		//touching one's own piece's corner (valid)
		assertTrue(gameBoard.placePiece(1, 5, pieceMatrix, 0, 0, 3, 3));
		//touching only another player's piece's corner (invalid)
		assertFalse(gameBoard.placePiece(2, 5, pieceMatrix, 2, 2, 16, 16));
	}
	
	private Piece createMonoPiece() {
		int[][] matrix = new int[1][1];
		matrix[0][0] = 1;
		// [ ]
		return new Piece(21, 1, matrix);
	}
	
	private Piece createDomPiece() {
		int[][] matrix = new int[2][1];
		matrix[0][0] = 1;
		matrix[1][0] = 1;
		// [ ][ ]
		return new Piece(20, 2, matrix);
	}
	
	private Piece createTriPiece() {
		int[][] matrix = new int[2][2];
		matrix[0][0] = 1;
		matrix[0][1] = 1;
		matrix[1][0] = 1;
		matrix[1][1] = 0;
		// [ ][ ]
		// [ ]
		return new Piece(18, 3, matrix);
	}
	
	private Piece createTetPiece() {
		int[][] matrix = new int[3][2];
		int[] line1 = {0, 1, 1};
		int[] line2 = {1, 1, 0};
		for(int j = 0; j < 2; j++) {
			for(int i = 0; i < 3; i++) {
				if(j == 0)
					matrix[i][j] = line1[i];
				else
					matrix[i][j] = line2[i];
			}
		}
		//   [ ][ ]
		//[ ][ ]
		return new Piece(13, 4, matrix);
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
	

}
