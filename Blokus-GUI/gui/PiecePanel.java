package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import logic.GuiActionTranslator;


public class PiecePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private GuiActionTranslator translator;
	
	OrientationPanel orient;
	PieceBankPanel[] pieceBanks;
	int currentPiecePanel;
	
	public PiecePanel(GuiActionTranslator translator) {
		super();
		this.translator = translator;
		init();
		addContent();
	}
	
	private void init() {
		pieceBanks = new PieceBankPanel[4];
		for(int i = 0; i < 4; i++) {
			pieceBanks[i] = new PieceBankPanel(translator, translator.getPlayerColorAsString(i));
		}
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(600, 800));
		setBackground(Color.BLUE);
	}
	
	private void addContent() {
		orient = new OrientationPanel(translator);
		currentPiecePanel = translator.getCurrentPlayer();
		add(orient, BorderLayout.SOUTH);
		add(pieceBanks[currentPiecePanel], BorderLayout.NORTH);
	}
	
	public void updatePiecePanel() {
		orient.updateOrientationPanel();
		updatePieceBankPanel();
	}
	
	private void updatePieceBankPanel() {
		remove(pieceBanks[currentPiecePanel]);
		currentPiecePanel = translator.getCurrentPlayer();
		add(pieceBanks[currentPiecePanel], BorderLayout.NORTH);
		revalidate();
	}
	
	public void updateOrientationPanel(int pieceID, String color) {
		orient.makePieceActive(pieceID, color);
	}
	
	public void removePieceFromPool(int playerID) {
		pieceBanks[currentPiecePanel].removePieceFromPool();
	}

}
