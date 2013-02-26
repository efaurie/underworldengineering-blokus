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
				boardMatrix[row][column] = 0;
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
				System.out.print(boardMatrix[j][i] + " ");
			}
			System.out.print("|\n");
		}
	}
	
	public void placePiece(int player, int xCoord, int yCoord) {
		if(isValidPlacement(player, xCoord, yCoord))
			boardMatrix[yCoord][xCoord] = player;
	}
	
	private boolean isValidPlacement(int player, int xCoord, int yCoord) {
		if(isSpaceFree(xCoord, yCoord) && isCornerConditionMet(player, xCoord, yCoord))
			return true;
		else
			return false;
	}
	
	private boolean isSpaceFree(int xCoord, int yCoord) {
		if(boardMatrix[yCoord][xCoord] == 0)
			return true;
		else
			return false;
	}
	
	private boolean isCornerConditionMet(int player, int x, int y) {
		if(isContactingPlayerPiece(player,x,y) || isCornerSquare(x,y))
			return true;
		else
			return false;
	}
	
	private boolean isContactingPlayerPiece(int player, int x, int y) {
		if((y+1 < BOARD_DIMENSION && x+1 < BOARD_DIMENSION) && boardMatrix[y + 1][x + 1] == player)
			return true;
		else if((y+1 < BOARD_DIMENSION && x-1 >= 0) && boardMatrix[y + 1][x - 1] == player)
			return true;
		else if((y-1 >= 0 && x+1 < BOARD_DIMENSION) && boardMatrix[y - 1][x + 1] == player)
			return true;
		else if((y-1 >= 0 && x-1 >= 0) && boardMatrix[y - 1][x - 1] == player)
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
	
}
