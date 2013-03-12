import static org.junit.Assert.*;
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
	public void testMonoPlacedInCenterOnFirstTurn() {
		Board gameBoard = new Board();
		Piece testPiece = createMonoPiece();
		int[][] pieceMatrix = testPiece.getMatrix();
		assertFalse(gameBoard.placePiece(1, 1, pieceMatrix, 0, 0, 10, 10));
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
	public void testMonoPlacedWithEdgeAgainstAnotherPiece() {
		Board gameBoard = new Board();
		Piece testPiece = createMonoPiece();
		int[][] pieceMatrix = testPiece.getMatrix();
		gameBoard.placePiece(1, 1, pieceMatrix, 0, 0, 0, 0);
		//against one's own piece (invalid)
		assertFalse(gameBoard.placePiece(1, 1, pieceMatrix, 0, 0, 1, 0));
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
	
	private Piece createMonoPiece() {
		int[][] matrix = new int[1][1];
		matrix[0][0] = 1;
		// [ ]
		return new Piece(1, matrix);
	}
	
	private Piece createDomPiece() {
		int[][] matrix = new int[2][1];
		matrix[0][0] = 1;
		matrix[0][1] = 1;
		// [ ][ ]
		return new Piece(2, matrix);
	}
	
	private Piece createTriPiece() {
		int[][] matrix = new int[2][2];
		matrix[0][0] = 1;
		matrix[0][1] = 1;
		matrix[1][0] = 1;
		matrix[1][1] = 0;
		// [ ][ ]
		// [ ]
		return new Piece(3, matrix);
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
		return new Piece(4, matrix);
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
		return new Piece(5, matrix);
	}
	

}
