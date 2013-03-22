import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;


public class PieceBankPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public PieceBankPanel() {
		init();
	}
	
	private void init() {
		setPreferredSize(new Dimension(600, 550));
		setBackground(Color.BLUE);
	}

}
