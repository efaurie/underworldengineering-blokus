import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;


public class BoardPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public BoardPanel() {
		super();
		setPreferredSize(new Dimension(500, 500));
		setBackground(Color.BLACK);
		setVisible(true);
	}
}
