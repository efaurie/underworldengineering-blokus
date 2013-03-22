import java.awt.Color;


public class GameController {
	
	private final int NUMBER_OF_PLAYERS = 4;
	private final Color[] COLORS = {Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE};
	private static PieceFactory pieceFactory;
	
	private boolean playing;
	private Board gameBoard;
	private Player[] players;
	private long startTime, endTime;
	private int currentPlayer;
	
	public GameController() {
		init();
	}
	
	private void init() {
		Lottery<Color> color = new Lottery<Color>(COLORS);
		pieceFactory = new PieceFactory();
		players = new Player[NUMBER_OF_PLAYERS];
		currentPlayer = 0;
		
		playing = false;
		gameBoard = new Board();
		for(int i = 0; i < NUMBER_OF_PLAYERS; i++) {
			players[i] = new Player(i, color.getNext());
			players[i].initPieces(pieceFactory);
		}
	}
	
	public void startGame() {
		if(playing == false) {
			playing = true;
			System.out.println("Playing Woot!");
		}
	}
	
	public void setPlayerName(int player, String newName) {
		players[player].setName(newName);
	}
	
	public void beginTurn() {
		startTime = System.currentTimeMillis();
	}
	
	public void endTurn() {
		endTime = System.currentTimeMillis();
		players[currentPlayer].addTurnLength(endTime - startTime);
		currentPlayer = (currentPlayer + 1) % NUMBER_OF_PLAYERS;
	}
	
	public void placePiece(Piece piece, int pieceCol, int pieceRow, int boardCol, int boardRow) {
		gameBoard.placePiece(currentPlayer, piece.getNumSquares(), piece.getMatrix(),
				pieceCol, pieceRow, boardCol, boardRow);
	}
	
	public void endGame() {
		
	}

}
