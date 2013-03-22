import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.JPanel;


public class OrientationPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	Image currentPiece;
	OrientationButtonPanel buttons;
	
	public OrientationPanel() {
		init();
		addContent();
	}
	
	private void init() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(600, 250));
		setBackground(Color.RED);
	}
	
	private void addContent() {
		buttons = new OrientationButtonPanel();
		add(buttons, BorderLayout.SOUTH);
	}

}
