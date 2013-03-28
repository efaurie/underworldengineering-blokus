package gui;

 

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import logic.GuiActionTranslator;


public class GamePlayPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private BoardPanel board;
	private ScorePanel score;
	private GuiActionTranslator translator;
	
	public GamePlayPanel(GuiActionTranslator translator) {
		super();
		this.translator = translator;
		init();
		addPanels();
	}
	
	private void init() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(600, 800));
		setBackground(Color.BLACK);
		board = new BoardPanel(translator);
		score = new ScorePanel(translator);
	}
	
	private void addPanels() {
		add(board, BorderLayout.SOUTH);
		add(score, BorderLayout.CENTER);
	}
	
	public void updateGamePlayPanel() {
		score.updateScorePanel();
		board.updateBoard();
	}
}
