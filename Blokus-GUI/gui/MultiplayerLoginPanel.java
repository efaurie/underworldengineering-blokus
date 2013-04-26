package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import logic.GuiActionTranslator;

public class MultiplayerLoginPanel {
	
	private JFrame frame;
	private JPanel[] playerPanels = new JPanel[4];
	private JPanel buttonPanel;
	private JPanel modePanel;
	private JTextField[] argFields = new JTextField[8];
	private JButton[] buttons = new JButton[6];
	private JRadioButton cruelMode;
	private JRadioButton kindMode;
	
	private GuiActionTranslator translator;
	private GameFrame parent;
	private MultiplayerLoginPanel self = this;
	
	public MultiplayerLoginPanel(GuiActionTranslator translator, GameFrame parent) {
		this.translator = translator;
		this.parent = parent;
		init();
	}
	
	private void init() {
		frame = new JFrame("Multiplayer Start");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setLayout(new GridLayout(0,1));
		for(int i = 0; i < 4; i++) {
			playerPanels[i] = new JPanel();
		}
		buttonPanel = new JPanel();
		modePanel = new JPanel();
		initFields();
		initButtons();
		initRadioButtons();
		buildPanels();
		addPanels();
		frame.pack();
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int positionX = (int)screen.getWidth()/2 - (int)frame.getSize().getWidth()/2;
		int positionY = (int)screen.getHeight()/2 - (int)frame.getSize().getHeight()/2;
		frame.setLocation(positionX, positionY);
		frame.setVisible(true);
	}
	
	private void initFields() {
		for(int i = 0; i < 8; i++) {
			if(i % 2 == 0) {
				argFields[i] = new JTextField(15);
			} else {
				argFields[i] = new JPasswordField(15);
			}
		}
	}
	
	private void initButtons() {
		for(int i = 0; i < 6; i++) {
			if(i < 4) {
				buttons[i] = new JButton("Login");
				registerLoginListener(buttons[i]);
			} else if(i == 4) {
				buttons[i] = new JButton("Play");
				registerPlayListener(buttons[i]);
			} else {
				buttons[i] = new JButton("Cancel");
				registerCancelListener(buttons[i]);
			}
			buttons[i].setFocusPainted(false);
		}
	}
	
	private void initRadioButtons() {
		cruelMode = new JRadioButton("Cruel Mode: ");
		cruelMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				self.setCruelMode();
			}
		});
		
		kindMode = new JRadioButton("Kind Mode: ");
		kindMode.setSelected(true);
		kindMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				self.setKindMode();
			}
		});
		
		ButtonGroup group = new ButtonGroup();
		group.add(cruelMode);
		group.add(kindMode);
		
	}
	
	private void buildPanels() {
		for(int i = 0; i < 4; i++) {
			int fieldGroup = 2*i;
			playerPanels[i].add(new JLabel("Player " + (i+1)));
			playerPanels[i].add(new JLabel("Username:"));
			playerPanels[i].add(argFields[fieldGroup]);
			playerPanels[i].add(new JLabel("Password:"));
			playerPanels[i].add(argFields[fieldGroup+1]);
			playerPanels[i].add(buttons[i]);
		}
		
		modePanel.add(kindMode);
		modePanel.add(cruelMode);
		
		buttonPanel.add(buttons[4]);
		buttonPanel.add(buttons[5]);
		
	}
	
	private void addPanels() {
		JPanel label = new JPanel();
		label.add(new JLabel("Players not logged in will play as guests"));
		frame.add(label);
		for(int i = 0; i < 4; i++) {
			frame.add(playerPanels[i]);
		}
		frame.add(modePanel);
		frame.add(buttonPanel);
	}
	
	private void setCruelMode() {
		translator.setCruelMode();
	}
	
	private void setKindMode() {
		translator.setKindMode();
	}
	
	private void registerLoginListener(JButton button) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = 0;
				JButton clicked = (JButton)e.getSource();
				for(int i = 0; i < 4; i++) {
					if(clicked == buttons[i])
						id = i;
				}
				String[] args = parseFields(id);
				if(args != null) {
					if(!translator.loginPlayer(id, args[0], args[1]))
						JOptionPane.showMessageDialog(new JFrame("Error"), "Something went wrong.\nLogin info incorrect.");
					else 
						JOptionPane.showMessageDialog(new JFrame("Success"), "Log In Successful.");
				}
			}
		});
	}
	
	private String[] parseFields(int id) {
		String[] result = null;
		int startField = 2*id;
		String username = argFields[startField].getText();
		String password = argFields[startField+1].getText();
		if(username.equals("") || password.equals("")) {
			JOptionPane.showMessageDialog(new JFrame("Error"), "Something went wrong.\nPerhaps you left a field blank.");
		} else {
			result = new String[2];
			result[0] = username;
			result[1] = password;
		}
		return result;
			
	}
	
	private void registerPlayListener(JButton button) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startGame();
				frame.dispose();
			}
		});
	}
	
	private void registerCancelListener(JButton button) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				translator.resetPlayers();
				frame.dispose();
			}
		});
	}
	
	private void startGame() {
		translator.multiplayStartGameAction();
        parent.startMPGame();
	}

}
