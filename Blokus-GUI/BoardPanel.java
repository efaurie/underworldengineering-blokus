import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class BoardPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private BufferedImage boardImage;
	private Image scaledBoardImage;
	private BufferedImage pieceImage;
	private Image scaledGreenPiece, scaledYellowPiece,
		scaledRedPiece, scaledBluePiece;
	private GuiActionTranslator translator;
	
	public BoardPanel(GuiActionTranslator translator) {
		super();
		this.translator = translator;
		setPreferredSize(new Dimension(600, 600));
		setBackground(Color.BLACK);
		getBoardImage();
		getPieceImages();
		
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
		getRedPiece();
		getBluePiece();
		getGreenPiece();
		getYellowPiece();
	}
	
	private void getRedPiece() {
		try {
			URL picLocation = BoardPanel.class.getResource("/res/red/1-1.png");
			pieceImage = ImageIO.read(picLocation);
			scaledRedPiece = pieceImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		} catch(IOException ioe) {
			System.out.println("Eh oh! The red piece image wasn't found!");
		}
	}
	
	private void getBluePiece() {
		try {
			URL picLocation = BoardPanel.class.getResource("/res/blue/1-1.png");
			pieceImage = ImageIO.read(picLocation);
			scaledBluePiece = pieceImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		} catch(IOException ioe) {
			System.out.println("Eh oh! The blue piece image wasn't found!");
		}
	}
	
	private void getGreenPiece() {
		try {
			URL picLocation = BoardPanel.class.getResource("/res/green/1-1.png");
			pieceImage = ImageIO.read(picLocation);
			scaledGreenPiece = pieceImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		} catch(IOException ioe) {
			System.out.println("Eh oh! The green piece image wasn't found!");
		}
	}
	
	private void getYellowPiece() {
		try {
			URL picLocation = BoardPanel.class.getResource("/res/yellow/1-1.png");
			pieceImage = ImageIO.read(picLocation);
			scaledYellowPiece = pieceImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		} catch(IOException ioe) {
			System.out.println("Eh oh! The yellow piece image wasn't found!");
		}
	}
	
	public void updateBoard() {
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(scaledBoardImage, 0, 0, null);
		//draw colored squares
	}

}
