import java.awt.Dimension;

import javax.swing.JFrame;


public class GameFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	GameData game;

	public GameFrame(GameData game) {
		super();
		this.game = game;
		init();
		addMenubar();
		displayFrame();
	}
	
	private void init() {
		setTitle("Blokus");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		showSplash();
		setMinimumSize(new Dimension(800, 600));
		setPreferredSize(new Dimension(1200, 800));
	}
	
	private void addMenubar() {
		GameMenuBar gameBar = new GameMenuBar(game);
		setJMenuBar(gameBar);
	}
	
	private void displayFrame() {
		pack();
		setVisible(true);
	}
	
	private void showSplash() {
		setContentPane(new SplashScreen());
		repaint();
	}
	
	public void play() {
		add(new BoardPanel());
		repaint();
	}

}
