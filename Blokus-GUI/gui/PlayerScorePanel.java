package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import logic.GuiActionTranslator;

public class PlayerScorePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private GuiActionTranslator translator;
	private int playerID;
	private String playerColor;
	private String playerName;
	private int playerScore;
	
	JLabel nameTag;
	JLabel scoreTag;
	JLabel avatarTag;
	
	JButton passButton;
	
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
		playerColor = translator.getPlayerColorAsString(playerID);
		playerName = translator.getPlayerName(playerID);
		playerScore = translator.getPlayerScore(playerID);
		setBackground(Color.GRAY);
		
		passButton = new JButton("Pass");
		passButton.setFocusPainted(false);
		registerButtonListener();
	}
	
	private void addContent() {
		nameTag = new JLabel(playerName);
		nameTag.setHorizontalAlignment(SwingConstants.CENTER);
		
		scoreTag = new JLabel("Score: " + playerScore);
		scoreTag.setHorizontalAlignment(SwingConstants.CENTER);
		
		try {
			String location = "/res/" + playerColor + "-avatar.png";
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
	
	private void registerButtonListener() {
		passButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Pass Clicked!");
				translator.passAction();
				updateInfo();
				releaseActive();
			}
		});
	}
	
	public void updateInfo() {
		scoreTag.setText("Score: " + translator.getPlayerScore(playerID));
		releaseActive();
	}
	
	public void makeActive() {
		remove(scoreTag);
		add(passButton, BorderLayout.SOUTH);
		revalidate();
	}
	
	public void releaseActive() {
		remove(passButton);
		add(scoreTag, BorderLayout.SOUTH);
		revalidate();
	}

}
