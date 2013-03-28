package gui;

 

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import logic.GuiActionTranslator;


public class ScorePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private GuiActionTranslator translator;
	PlayerScorePanel[] panels;

	public ScorePanel(GuiActionTranslator translator) {
		this.translator = translator;
		init();
		addPlayers();
		setNewActivePlayer();
	}
	
	private void addPlayers() {
		for(int i = 0; i < 4; i++) {
			panels[i] = new PlayerScorePanel(translator, i);
			add(panels[i]);
		}
	}
	
	private void init() {
		setPreferredSize(new Dimension(600, 200));
		setLayout(new FlowLayout());
		setBackground(Color.GRAY);
		panels = new PlayerScorePanel[4];
	}
	
	private void updateInfo() {
		for(int i = 0; i < 4; i++) {
			panels[i].updateInfo();
		}
	}
	
	private void setNewActivePlayer() {
		int index = translator.getCurrentPlayer();
		panels[index].makeActive();
	}
	
	public void updateScorePanel() {
		updateInfo();
		setNewActivePlayer();
	}

}
