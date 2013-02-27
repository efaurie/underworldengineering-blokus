public class Board {

	int[][] boardMatrix;
	private final int BOARD_DIMENSION = 20;
	
	public Board() {
		initializeBoard();
	}
	
	private void initializeBoard() {
		boardMatrix = new int[BOARD_DIMENSION][BOARD_DIMENSION];
		zeroOutMatrix();
	}
	
	private void zeroOutMatrix() {
		for(int row = 0; row < BOARD_DIMENSION; row++) {
			for(int column = 0; column < BOARD_DIMENSION; column++) {
				boardMatrix[column][row] = 0;
			}
		}
	}
	
	public void printBoard() {
		printHorizontalBoarder();
		printRows();
		printHorizontalBoarder();
	}
	
	private void printHorizontalBoarder() {
		System.out.print(" ");
		for(int i = 0; i < 20; i++) {
			System.out.print("--");
		}
		System.out.print("\n");
	}
	
	private void printRows() {
		for(int j = 0; j < BOARD_DIMENSION; j++) {
			System.out.print("| ");
			for(int i = 0; i < BOARD_DIMENSION; i++) {
				System.out.print(boardMatrix[i][j] + " ");
			}
			System.out.print("|\n");
		}
	}
	
	public void placePiece(int player, int squares, int[][] piece, int pieceCol, int pieceRow,
			int boardCol, int boardRow) {
		int[][] positions = translateToPositions(squares, piece, pieceCol, pieceRow, boardCol, boardRow);
		if(isValidPlacement(player, positions))
			markPiece(player, positions);
	}
	
	private void markPiece(int player, int[][] positions) {
		for(int i = 0; i < positions.length; i++) {
			boardMatrix[positions[i][0]][positions[i][1]] = player;
		}
	}
	
	private int[][] translateToPositions(int squares, int[][] piece, int pCol, int pRow, int bCol, int bRow) {
		int[][] positions = new int[squares][2];
		int currentSquare = 0;
		for(int i = 0; i < piece[0].length; i++) {
			for(int j = 0; j < piece.length; j++) {
				if(piece[j][i] == 1) {
					int xDif, yDif;
					xDif = j - pCol;
					yDif = i - pRow;
					positions[currentSquare][0] = bCol + xDif;
					positions[currentSquare][1] = bRow + yDif;
					currentSquare++;
				}
			}
		}
		return positions;
	}
	
	private boolean isValidPlacement(int player, int[][] positions) {
		if(areSpacesFree(positions) && isCornerConditionMet(player, positions) && 
				isEdgeConditionMet(player, positions))
			return true;
		else
			return false;
	}
	
	private boolean areSpacesFree(int[][] positions) {
		for(int i = 0; i < positions.length; i++) {
			if(!isSpaceFree(positions[i][0], positions[i][1]))
				return false;
		}
		return true;
	}
	
	private boolean isSpaceFree(int xCoord, int yCoord) {
		if(xCoord > BOARD_DIMENSION - 1 || yCoord > BOARD_DIMENSION - 1)
			return false;
		else if(xCoord < 0 || yCoord < 0)
			return false;
		if(boardMatrix[xCoord][yCoord] == 0)
			return true;
		else
			return false;
	}
	
	private boolean isCornerConditionMet(int player, int[][] positions) {
		for(int i = 0; i < positions.length; i++) {
			if(checkCornerCondition(player, positions[i][0], positions[i][1]))
				return true;
		}
		return false;
	}
	
	private boolean checkCornerCondition(int player, int x, int y) {
		if(isContactingPlayerCorner(player,x,y) || isCornerSquare(x,y))
			return true;
		else
			return false;
	}
	
	
	private boolean isContactingPlayerCorner(int player, int x, int y) {
		if((y+1 < BOARD_DIMENSION && x+1 < BOARD_DIMENSION) && boardMatrix[x + 1][y + 1] == player)
			return true;
		else if((y+1 < BOARD_DIMENSION && x-1 >= 0) && boardMatrix[x - 1][y + 1] == player)
			return true;
		else if((y-1 >= 0 && x+1 < BOARD_DIMENSION) && boardMatrix[x + 1][y - 1] == player)
			return true;
		else if((y-1 >= 0 && x-1 >= 0) && boardMatrix[x - 1][y - 1] == player)
			return true;
		else
			return false;
	}
	
	private boolean isCornerSquare(int x, int y) {
		if(x == 0 && y == 0)
			return true;
		else if(x == BOARD_DIMENSION - 1 && y == BOARD_DIMENSION - 1)
			return true;
		else if(x == 0 && y == BOARD_DIMENSION - 1)
			return true;
		else if(x == BOARD_DIMENSION - 1 && y == 0)
			return true;
		else
			return false;
	}
	
	private boolean isEdgeConditionMet(int player, int[][] positions) {
		for(int i = 0; i < positions.length; i++) {
			if(isContactingEdge(player, positions[i][0], positions[i][1]))
				return false;
		}
		return true;
	}
	
	private boolean isContactingEdge(int player, int x, int y) {
		if(y+1 < BOARD_DIMENSION && boardMatrix[x][y+1] == player)
			return true;
		else if(x+1 < BOARD_DIMENSION && boardMatrix[x+1][y] == player)
			return true;
		else if(y-1 >= 0 && boardMatrix[x][y-1] == player)
			return true;
		else if(x-1 >= 0 && boardMatrix[x-1][y] == player)
			return true;
		else
			return false;
	}
	
}
