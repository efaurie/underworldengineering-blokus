import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;


public class GameFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	public GameFrame() {
		super();
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
		GameMenuBar gameBar = new GameMenuBar();
		setJMenuBar(gameBar);
	}
	
	private void displayFrame() {
		pack();
		setVisible(true);
	}
	
	private void showSplash() {
		setContentPane(new SplashScreen());
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
