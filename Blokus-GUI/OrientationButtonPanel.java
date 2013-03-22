import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;


public class OrientationButtonPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	JButton rotateCounter;
	JButton rotateClock;
	JButton flipVertical;
	JButton flipHorizontal;
	
	public OrientationButtonPanel() {
		init();
		addContent();
	}
	
	private void init() {
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(600, 35));
		setBackground(Color.RED);
	}
	
	private void addContent() {
		JButton rotateCounter = new JButton("Rotate CCW");
		rotateCounter.setFocusPainted(false);
		JButton rotateClock = new JButton("Rotate CW");
		rotateClock.setFocusPainted(false);
		JButton flipVertical = new JButton("Flip");
		flipVertical.setFocusPainted(false);
		JButton flipHorizontal = new JButton("Turn");
		flipHorizontal.setFocusPainted(false);
		add(rotateClock);
		add(flipVertical);
		add(flipHorizontal);
		add(rotateCounter);
	}

}
