package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import logic.GuiActionTranslator;


public class BoardPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final int SCALED_BLOCK_WIDTH = 600 / 20;
	private static final int SCALED_BLOCK_HEIGHT = 600 / 20;
	
	private BufferedImage boardImage;
	private Image scaledBoardImage;
	private BufferedImage pieceImage;
	private Image scaledPiece;
	private Image pieces[];
	private GuiActionTranslator translator;
	
	
	public BoardPanel(GuiActionTranslator translator) {
		super();
		this.translator = translator;
		pieces = new Image[4];
		setPreferredSize(new Dimension(600, 600));
		setBackground(Color.BLACK);
		getBoardImage();
		getPieceImages();
		addMouseInput();
	}
	
	private void getBoardImage() {
		try {
			URL picLocation = BoardPanel.class.getResource("/res/board.png");
			boardImage = ImageIO.read(picLocation);
			scaledBoardImage = boardImage.getScaledInstance(600, 600, Image.SCALE_SMOOTH);
		} catch(IOException ioe) {
			System.out.println("Eh oh! The board image wasn't found!");
		}
	}
	
	private void getPieceImages() {
		String color;
		for(int i = 0; i < 4; i++) {
			color = translator.getPlayerColorAsString(i);
			pieces[i] = getPieceImage(color);
		}
	}
	
	private Image getPieceImage(String color) {
		try {
			URL picLocation = BoardPanel.class.getResource("/res/" + color + "/21.png");
			pieceImage = ImageIO.read(picLocation);
			scaledPiece = pieceImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		} catch(IOException ioe) {
			System.out.println("Eh oh! A piece image wasn't found!");
		}
		return scaledPiece;
	}
	
	private void addMouseInput() {
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Point boardCoords = translateToCoord(e.getX(), e.getY());
				translator.boardCoordsSelected(boardCoords);
			}
		});
	}
	
	private Point translateToCoord(int x, int y) {
		int xCoord = x / SCALED_BLOCK_WIDTH;
		int yCoord = y / SCALED_BLOCK_HEIGHT;
		System.out.println("Clicked on Board Coord: (" + xCoord + ", " + yCoord + ")");
		return new Point(xCoord, yCoord);
	}
	
	public void updateBoard() {
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(scaledBoardImage, 0, 0, null);
		int[][] matrix = translator.getBoardMatrix();
		for(int i = 0; i < 20; i++) {
			for(int j = 0; j < 20; j++) {
				if(matrix[j][i] != 0) {
					Point placement = translateToPicLocation(j, i);
					g.drawImage(pieces[matrix[j][i] - 1], (int)placement.getX(),
							(int)placement.getY(), null);
				}
			}
		}
	}
	
	private Point translateToPicLocation(int x, int y) {
		int xCoord = x * SCALED_BLOCK_WIDTH;
		int yCoord = y * SCALED_BLOCK_WIDTH;
		return new Point(xCoord, yCoord);
	}

}
