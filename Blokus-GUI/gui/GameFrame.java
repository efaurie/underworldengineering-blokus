package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import logic.GuiActionTranslator;


public class GameFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	GuiActionTranslator translator;
	GamePanel gamePanel;
	SplashScreen splashPanel;

	public GameFrame(GuiActionTranslator translator) {
		super();
		this.translator = translator;
		init();
		addMenubar();
		displayFrame();
	}
	
	private void init() {
		translator.registerFrame(this);
		setTitle("Blokus");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		showSplash();
		setPreferredSize(new Dimension(1200, 800));
		setResizable(false);
	}
	
	private void addMenubar() {
		GameMenuBar gameBar = new GameMenuBar(this, translator);
		setJMenuBar(gameBar);
	}
	
	private void displayFrame() {
		pack();
		setVisible(true);
	}
	
	private void showSplash() {
		splashPanel = new SplashScreen(); 
		add(splashPanel, BorderLayout.CENTER);
	}
	
	private void showScoreScreen() {
		remove(gamePanel);
		showSplash();
		revalidate();
		repaint();
	}
	
	public void startMPGame() {
		remove(splashPanel);
		gamePanel = new GamePanel(translator);
		add(gamePanel, BorderLayout.CENTER);
		revalidate();
		repaint();
		pack();
	}
	
	public void updateGameFrame() {
		gamePanel.updateGamePanel();
	}
	
	public void updateOrientationPanel(int pieceID, String color) {
		gamePanel.updateOrientationPanel(pieceID, color);
	}
	
	public void removePieceFromPool(int playerID) {
		gamePanel.removePieceFromPool(playerID);
	}
	
	public void endGame() {
		showScoreScreen();
	}

}
