package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logic.GuiActionTranslator;


public class OrientationPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final double SCALE = 3.5;
	private static final int SCALED_BLOCK_WIDTH = (int)(10 * SCALE);
	private static final int SCALED_BLOCK_HEIGHT = (int)(10 * SCALE);
	
	GuiActionTranslator translator;
	BufferedImage pieceImage;
	ImageIcon scaledImage;
	OrientationButtonPanel buttons;
	String locationString;
	JPanel labelPanel;
	JLabel currentPiece;
	
	public OrientationPanel(GuiActionTranslator translator) {
		this.translator = translator;
		init();
		addContent();
	}
	
	private void init() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(600, 258));
		setBackground(Color.BLACK);
	}
	
	private void addContent() {
		labelPanel = new JPanel();
		labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
		labelPanel.setBackground(Color.BLACK);
		
		
		currentPiece = new JLabel();
		currentPiece.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelPanel.add(Box.createVerticalGlue());
		labelPanel.add(currentPiece);
		labelPanel.add(Box.createVerticalGlue());
		add(labelPanel, BorderLayout.CENTER);
		
		registerLabelListener();
		buttons = new OrientationButtonPanel(this);
		add(buttons, BorderLayout.SOUTH);
	}
	
	public void updateOrientationPanel() {
		currentPiece.setIcon(null);
		scaledImage = null;
	}
	
	public void makePieceActive(int pieceID, String color) {
		locationString = "/res/" + color + "/" + pieceID + ".png";
		setPieceImage();
	}
	
	private void setPieceImage() {
		try {
			URL picLocation = PieceImageLabel.class.getResource(locationString);
			pieceImage = ImageIO.read(picLocation);
		} catch(IOException ioe) {
			System.out.println("Eh oh! The piece image wasn't found!");
		}
		scaleAndSet();
	}
	
	public void rotate(int angle) {
		if(pieceSelected()) {
			
			BufferedImage oldImage = pieceImage;
			BufferedImage newImage = new BufferedImage(oldImage.getHeight(), oldImage.getWidth(), oldImage.getType());
			Graphics2D graphics = (Graphics2D) newImage.getGraphics();
			graphics.rotate(Math.toRadians(angle), newImage.getWidth() / 2, newImage.getHeight() / 2);
			graphics.translate((newImage.getWidth() - oldImage.getWidth()) / 2, (newImage.getHeight() - oldImage.getHeight()) / 2);
			graphics.drawImage(oldImage, 0, 0, oldImage.getWidth(), oldImage.getHeight(), null);
			
			pieceImage = newImage;
			scaleAndSet();
			
			translator.rotatePiece(angle);
		}
	}
	
	public void flip() {
		if(pieceSelected()) {
			AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
			tx.translate(0, -pieceImage.getHeight(null));
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			pieceImage = op.filter(pieceImage, null);
			scaleAndSet();
			
			translator.flipPiece();
		}
	}
	
	public void turn() {
		if(pieceSelected()) {
			AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
			tx.translate(-pieceImage.getWidth(null), 0);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			pieceImage = op.filter(pieceImage, null);
			scaleAndSet();
			
			translator.turnPiece();
		}
	}
	
	private void scaleAndSet() {
		int height = (int)(pieceImage.getHeight() * SCALE);
		int width = (int)(pieceImage.getWidth() * SCALE);
		scaledImage = new ImageIcon(pieceImage.getScaledInstance(width, height, Image.SCALE_SMOOTH));
		currentPiece.setIcon(scaledImage);
		currentPiece.setMaximumSize(new Dimension(width, height));
		revalidate();
		repaint();
	}
	
	private boolean pieceSelected() {
		if(scaledImage == null)
			return false;
		else
			return true;
	}
	
	private void registerLabelListener() {
		currentPiece.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Point pieceCoords = translateToCoord(e.getX(), e.getY());
				translator.pieceCoordsSelected(pieceCoords);
			}
		});
	}
	
	private Point translateToCoord(int x, int y) {
		int xBuffer = currentPiece.getWidth() / SCALED_BLOCK_WIDTH;
		int yBuffer = currentPiece.getHeight() / SCALED_BLOCK_HEIGHT;
		int xCoord = x / (SCALED_BLOCK_WIDTH + xBuffer);
		int yCoord = y / (SCALED_BLOCK_HEIGHT + yBuffer);
		System.out.println("Clicked on Piece Coord: (" + xCoord + ", " + yCoord + ")");
		return new Point(xCoord, yCoord);
	}

}
