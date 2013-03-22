import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class GameFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	GuiActionTranslator translator;
	JPanel currentPanel;

	public GameFrame(GuiActionTranslator translator) {
		super();
		this.translator = translator;
		init();
		addMenubar();
		displayFrame();
	}
	
	private void init() {
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
		currentPanel = new SplashScreen(); 
		add(currentPanel, BorderLayout.CENTER);
	}
	
	public void startMPGame() {
		remove(currentPanel);
		currentPanel = new GamePanel(translator);
		add(currentPanel, BorderLayout.CENTER);
		revalidate();
		repaint();
		pack();
	}

}
