import java.awt.Color;


public class GameData {
	
	private final int NUMBER_OF_PLAYERS = 4;
	private final Color[] COLORS = {Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE};
	private static PieceFactory pieceFactory;
	
	private GameFrame frame;
	
	private boolean playing;
	private Board gameBoard;
	private Player[] players;
	
	public GameData() {
		init();
	}
	
	private void init() {
		frame = new GameFrame(this);
		Lottery<Color> color = new Lottery<Color>(COLORS);
		pieceFactory = new PieceFactory();
		players = new Player[NUMBER_OF_PLAYERS];
		
		playing = false;
		gameBoard = new Board();
		for(int i = 0; i < NUMBER_OF_PLAYERS; i++) {
			players[i] = new Player(i, color.getNext());
			players[i].initPieces(pieceFactory);
		}
	}
	
	public void start() {
		if(playing == false) {
			playing = true;
			System.out.println("Playing Woot!");
			frame.play();
		}
	}

}
