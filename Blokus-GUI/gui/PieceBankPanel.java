package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import logic.GuiActionTranslator;


public class PieceBankPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private GuiActionTranslator translator;
	private String playerColor;
	private PieceImageLabel[] imageLabels;
	private PieceImageLabel currentlySelected;

	public PieceBankPanel(GuiActionTranslator translator, String color) {
		this.translator = translator;
		playerColor = color;
		init();
		addContent();
	}
	
	private void init() {
		setLayout(new GridLayout(5, 5));
		setPreferredSize(new Dimension(600, 500));
		setBackground(Color.GRAY);
		imageLabels = new PieceImageLabel[21];
		currentlySelected = null;
	}
	
	private void addContent() {
		createPieceImageLabels();
		for(int i = 0; i < 21; i++) {
			add(imageLabels[i]);
		}
	}
	
	private void createPieceImageLabels() {
		for(int i = 0; i < 21; i++) {
			imageLabels[i] = new PieceImageLabel(translator, playerColor, i+1, this);
		}
	}
	
	public void setCurrentlySelected(PieceImageLabel select) {
		currentlySelected = select;
	}
	
	public void updatePieceBankPanel() {
		
	}
	
	public void removePieceFromPool() {
		if(currentlySelected != null) {
			currentlySelected.deactivate();
			remove(currentlySelected);
			revalidate();
			repaint();
		}
	}
}
