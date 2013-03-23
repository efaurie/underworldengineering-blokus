package gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import logic.Piece;


public class PieceFactory {
	
	private ArrayList<Piece> pieces;
	
	public ArrayList<Piece> generatePieces() {
		pieces = new ArrayList<Piece>();
		BufferedReader input = accessFile();
		readFileAndGenerate(input);
		return pieces;
	}
	
	private BufferedReader accessFile() {
		InputStream input = PieceFactory.class.getResourceAsStream("/pieces.txt");
		return new BufferedReader(new InputStreamReader(input));
	}
	
	private void readFileAndGenerate(BufferedReader input) {
		String currentLine = null;
		String[] stringArray = null;
		int rows, columns, squares, id;
		try {
			while((currentLine = input.readLine()) != null) {
				stringArray = currentLine.split(",");
				rows = Integer.parseInt(stringArray[0]);
				columns = Integer.parseInt(stringArray[1]);
				squares = Integer.parseInt(stringArray[2]);
				id = Integer.parseInt(stringArray[3]);
				
				int[][] matrix = new int[columns][rows];
				for(int i = 0; i < rows; i++) {
					currentLine = input.readLine();
					stringArray = currentLine.split(",");
					for(int j = 0; j < columns; j++) {
						matrix[j][i] = Integer.parseInt(stringArray[j]);
					}
				}
				createPiece(id, squares, matrix);
				input.readLine();
			}
		} catch(IOException e) {
			System.out.println("Error reading in piece file");
		}
	}
	
	private void createPiece(int id, int squares, int[][] matrix) {
		pieces.add(new Piece(id, squares, matrix));
	}

}
