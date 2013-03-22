import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;


public class GamePlayPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private BoardPanel board;
	private PiecePanel pieces;

	public GamePlayPanel() {
		super();
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(500, 500));
		setVisible(true);
	}

}
