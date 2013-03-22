import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;


public class PiecePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private GuiActionTranslator translator;
	
	OrientationPanel orient;
	PieceBankPanel pieceBank;
	
	public PiecePanel(GuiActionTranslator translator) {
		super();
		this.translator = translator;
		init();
		addContent();
	}
	
	private void init() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(600, 800));
		setBackground(Color.BLUE);
	}
	
	private void addContent() {
		orient = new OrientationPanel();
		pieceBank = new PieceBankPanel();
		add(pieceBank, BorderLayout.NORTH);
		add(orient, BorderLayout.SOUTH);
	}

}
