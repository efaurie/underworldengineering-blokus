package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import logic.GuiActionTranslator;

public class PieceImageLabel extends JLabel {
	private static final long serialVersionUID = 1L;
	private static final double SCALE = 2.0;
	
	private GuiActionTranslator translator;
	private int pieceID;
	private String locationString;
	private String color;
	private BufferedImage pieceImage;
	private ImageIcon scaledImage;
	private boolean active;
	private PieceBankPanel parent;

	public PieceImageLabel(GuiActionTranslator translator, String color, int pieceID, PieceBankPanel parent) {
		this.translator = translator;
		this.pieceID = pieceID;
		this.color = color;
		this.parent = parent;
		locationString = "/res/" + color + "/" + pieceID + ".png";
		init();
		addContent();
	}
	
	private void init() {
		active = true;
		setPreferredSize(new Dimension(100, 100));
		setBackground(Color.GRAY);
		setHorizontalAlignment(SwingConstants.CENTER);
		setVerticalAlignment(SwingConstants.CENTER);
		addMouseInput();
	}
	
	private void addContent() {
		try {
			URL picLocation = PieceImageLabel.class.getResource(locationString);
			pieceImage = ImageIO.read(picLocation);
			int height = (int)(pieceImage.getHeight() * SCALE);
			int width = (int)(pieceImage.getWidth() * SCALE);
			scaledImage = new ImageIcon(pieceImage.getScaledInstance(width, height, Image.SCALE_SMOOTH));
		} catch(IOException ioe) {
			System.out.println("Eh oh! The piece image wasn't found!");
		}
		setIcon(scaledImage);
	}
	
	private void addMouseInput() {
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(active) {
					translator.piecePanelClickAction(pieceID, color);
					notifyParent();
				}
			}
		});
	}
	
	private void notifyParent() {
		parent.setCurrentlySelected(this);
	}
	
	public void deactivate() {
		active = false;
	}

}
