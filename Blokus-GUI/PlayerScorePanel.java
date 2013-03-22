import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PlayerScorePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private GuiActionTranslator translator;
	private int playerID;
	private Color playerColor;
	private String playerName;
	private int playerScore;
	
	JLabel nameTag;
	JLabel scoreTag;
	JLabel avatarTag;
	
	BufferedImage avatar;
	Image scaledAvatar;
	ImageIcon iconAvatar;
	
	public PlayerScorePanel(GuiActionTranslator translator, int playerID) {
		this.translator = translator;
		this.playerID = playerID;
		init();
		addContent();
	}
	
	private void init() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(120, 150));
		playerColor = translator.getPlayerColor(playerID);
		playerName = translator.getPlayerName(playerID);
		playerScore = translator.getPlayerScore(playerID);
		setBackground(Color.GRAY);
	}
	
	private void addContent() {
		nameTag = new JLabel(playerName);
		nameTag.setHorizontalAlignment(SwingConstants.CENTER);
		
		scoreTag = new JLabel("Score: " + playerScore);
		scoreTag.setHorizontalAlignment(SwingConstants.CENTER);
		
		try {
			String location;
			if(playerColor == Color.BLUE)
				location = "/res/blue-avatar.png";
			else if(playerColor == Color.RED)
				location = "/res/red-avatar.png";
			else if(playerColor == Color.GREEN)
				location = "/res/green-avatar.png";
			else
				location = "/res/yellow-avatar.png";
			URL picLocation = PlayerScorePanel.class.getResource(location);
			avatar = ImageIO.read(picLocation);
			scaledAvatar = avatar.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			iconAvatar = new ImageIcon(scaledAvatar);
		} catch(IOException ioe) {
			System.out.println("Eh oh! The avatar image wasn't found!");
		}
		avatarTag = new JLabel();
		avatarTag.setHorizontalAlignment(SwingConstants.CENTER);
		avatarTag.setPreferredSize(new Dimension(100, 100));
		avatarTag.setIcon(iconAvatar);
		
		add(nameTag, BorderLayout.NORTH);
		add(avatarTag, BorderLayout.CENTER);
		add(scoreTag, BorderLayout.SOUTH);
	}
	
	public void updateInfo() {
		
	}

}
