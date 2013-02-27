import java.awt.Color;
import java.util.Iterator;
import java.util.Scanner;


public class Game {
	
	private static final Color[] COLORS = {Color.RED, Color.BLUE,
		Color.GREEN, Color.YELLOW};
	private static final int NUMBER_OF_PLAYERS = 4;
	
	private static PieceFactory pieceFactory;
	private static Scanner input = new Scanner(System.in);
	
	private static boolean playing;
	private static Board gameBoard;
	private static Player[] players; 
	
	public static void main(String[] args) {
		init();
		start();
	}
	
	private static void init() {
		Lottery<Color> color = new Lottery<Color>(COLORS);
		pieceFactory = new PieceFactory();
		players = new Player[NUMBER_OF_PLAYERS];
		
		playing = true;
		gameBoard = new Board();
		for(int i = 0; i < NUMBER_OF_PLAYERS; i++) {
			players[i] = new Player(i, color.getNext());
			players[i].initPieces(pieceFactory);
		}
	}
	
	private static void start() {
		playing = true;
		int currentPlayer = 0;
		while(playing) {
			playTurn(currentPlayer);
			playing = checkEndGameConditions();
			currentPlayer = (currentPlayer + 1) % NUMBER_OF_PLAYERS;
		}
	}
	
	private static void playTurn(int player) {
		long startTime = System.currentTimeMillis();
		gameBoard.printBoard();
		int selection = printOptions(player);
		if(selection == 1)
			placePiece(player);
		long endTime = System.currentTimeMillis();
		players[player].addTurnLength(endTime - startTime);
		System.out.println("Your turn took " + (endTime - startTime)/1000.0 +
				" seconds, your total control time is " + 
				players[player].getTimeInControlSec() + " seconds.\n");
	}
	
	private static int printOptions(int player) {
		int selection;
		System.out.print("It's your turn Player " + (player + 1) + "!\n");
		System.out.print("\t1. Place Piece \n");
		System.out.print("\t2. Pass \n");
		System.out.print("\tInput: ");
		selection = Integer.parseInt(input.nextLine());
		return selection;
	}
	
	private static void placePiece(int player) {
		System.out.println("Pieces in Pool: \n");
		Iterator<Piece> pool = players[player].getPieceIterator();
		for(int i = 0; i < players[player].getNumPiecesRemaining(); i++) {
			System.out.println("Piece ID: " + i);
			pool.next().printPiece();
			System.out.println();
		}
		System.out.print("Place piece with ID: ");
		int selection = Integer.parseInt(input.nextLine());
		Piece activePiece = players[player].getPiece(selection);
		activePiece.printPiece();
		boolean positioning = true;
		while(positioning) {
			selection = printRotationMenu();
			switch(selection) {
			case 1:
				activePiece.rotateClockwise();
				break;
			case 2:
				activePiece.rotateCounterClockwise();
				break;
			case 3:
				activePiece.mirrorOverHorizontal();
				break;
			case 4:
				activePiece.mirrorOverVertical();
				break;
			case 5:
				positioning = false;
				break;
			}
			activePiece.printPiece();
		}
		System.out.print("\nPlace Piece (p_column, p_row, b_column, b_row): ");
		String inString = input.nextLine();
		String[] inArray = inString.split(",");
		int[] points = new int[4];
		for(int i = 0; i < 4; i++) {
			points[i] = Integer.parseInt(inArray[i]);
		}
		gameBoard.placePiece(player+1, activePiece.getNumSquares(), activePiece.getMatrix(),
				points[0], points[1],
				points[2], points[3]);
		players[player].removePiece(activePiece);
	}
	
	private static int printRotationMenu() {
		System.out.println("\t1. Rotate Clockwise");
		System.out.println("\t2. Rotate Counter Clockwise");
		System.out.println("\t3. Flip (Up/Down)");
		System.out.println("\t4. Flip (Left/Right)");
		System.out.println("\t5. Place");
		System.out.print("\tInput: ");
		return Integer.parseInt(input.nextLine());
	}
	
	private static boolean checkEndGameConditions() {
		boolean stillPlaying = true;
		return stillPlaying;
	}

}
