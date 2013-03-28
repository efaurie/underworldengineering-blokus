package gui;

 

import java.awt.BorderLayout;

import javax.swing.JPanel;

import logic.GuiActionTranslator;


public class GamePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private GuiActionTranslator translator;
	private GamePlayPanel board;
	private PiecePanel pieces;

	public GamePanel(GuiActionTranslator translator) {
		super();
		this.translator = translator;
		setLayout(new BorderLayout());
		addBoard();
		addPieces();
	}
	
	private void addBoard() {
		board = new GamePlayPanel(translator);
		add(board, BorderLayout.WEST);
	}
	
	private void addPieces() {
		pieces = new PiecePanel(translator);
		add(pieces, BorderLayout.EAST);
	}
	
	public void updateGamePanel() {
		board.updateGamePlayPanel();
		pieces.updatePiecePanel();
		repaint();
	}
	
	public void updateOrientationPanel(int pieceID, String color) {
		pieces.updateOrientationPanel(pieceID, color);
	}
	
	public void removePieceFromPool(int playerID) {
		pieces.removePieceFromPool(playerID);
	}

}
