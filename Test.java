import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;


public class Test {

	static Board testBoard;
	static Scanner input = new Scanner(System.in);
	static ArrayList<Monomino> monominoes = new ArrayList<Monomino>();
	static ArrayList<Domino> dominoes = new ArrayList<Domino>();
	static ArrayList<Triomino> triominoes = new ArrayList<Triomino>();
	static ArrayList<Tetromino> tetrominoes = new ArrayList<Tetromino>();
	static ArrayList<Pentomino> pentominoes = new ArrayList<Pentomino>();
	
	public static void main(String[] args) {
		testBoard = new Board();
		generatePieces();
		printPieces();
		//testBoard.printBoard();
	}
	
	public static void generatePieces() {
		BufferedReader input = accessFile();
		readFile(input);
	}
	
	private static BufferedReader accessFile() {
		InputStream input = Test.class.getResourceAsStream("/pieces.txt");
		return new BufferedReader(new InputStreamReader(input));
	}
	
	private static void readFile(BufferedReader input) {
		String currentLine = null;
		try {
			while((currentLine = input.readLine()) != null) {
				int dimension = Integer.parseInt(currentLine);
				int[][] matrix = new int[dimension][dimension];
				for(int i = 0; i < dimension; i++) {
					currentLine = input.readLine();
					String[] stringArray = currentLine.split(",");
					for(int j = 0; j < dimension; j++) {
						matrix[i][j] = Integer.parseInt(stringArray[j]);
					}
				}
				createPiece(dimension, matrix);
				input.readLine();
			}
		} catch(IOException e) {
			System.out.println("Error reading in piece file");
		}
	}
	
	private static void createPiece(int dimension, int[][] matrix) {
		switch(dimension) {
		case 1:
			monominoes.add(new Monomino(Color.RED, matrix));
			break;
		case 2: 
			dominoes.add(new Domino(Color.RED, matrix));
			break;
		case 3:
			triominoes.add(new Triomino(Color.RED, matrix));
			break;
		case 4:
			tetrominoes.add(new Tetromino(Color.RED, matrix));
			break;
		case 5:
			pentominoes.add(new Pentomino(Color.RED, matrix));
			break;
		}
	}
	
	private static void printPieces() {
		for(Pentomino current : pentominoes) {
			current.printMatrix();
			System.out.println();
			current.rotateClockwise();
			current.printMatrix();
		}
	}
	
}
