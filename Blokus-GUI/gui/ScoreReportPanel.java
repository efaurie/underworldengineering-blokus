package gui;

 

import java.awt.FlowLayout;
import java.awt.GridLayout;
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

import logic.GuiActionTranslator;
import logic.ScoreReport;

public class ScoreReportPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private GuiActionTranslator translator;
	private BufferedImage avatar;
	private Image[] scaledAvatar;
	private ScoreReport report;
	private JPanel[] players;
	private JButton done;
	
	public ScoreReportPanel(GuiActionTranslator translator) {
		this.translator = translator;
		report = translator.getReport();
		init();
		addContentToPanels();
		addContent();
	}
	
	private void init() {
		setLayout(new GridLayout(0, 1));
		players = new JPanel[4];
		scaledAvatar = new Image[4];
		
		done = new JButton("Done");
		registerDoneAction();
		
		String[] colors = report.getColors();
		for(int i = 0; i < 4; i++) {
			players[i] = new JPanel();
			players[i].setLayout(new FlowLayout());
			try {
				String location = "/res/" + colors[i] + "-avatar.png";
				URL picLocation = ScoreReportPanel.class.getResource(location);
				avatar = ImageIO.read(picLocation);
				scaledAvatar[i] = avatar.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			} catch(IOException ioe) {
				System.out.println("Eh oh! The avatar image wasn't found!");
			}
		}
	}
	
	private void addContentToPanels() {
		String[] names = report.getNames();
		String[] times = report.getTimes();
		int[] scores = report.getScores();
		
		for(int i = 0; i < 4; i++) {
			JLabel avatarLabel = new JLabel();
			avatarLabel.setIcon(new ImageIcon(scaledAvatar[i]));
			players[i].add(avatarLabel);
			players[i].add(new JLabel(names[i]));
			players[i].add(new JLabel("Score: " + scores[i]));
			players[i].add(new JLabel("Time In Control: " + times[i]));
		}
	}
	
	private void addContent() {
		for(int i = 0; i < 4; i++) {
			add(players[i]);
		}
		add(done);
	}
	
	private void registerDoneAction() {
		done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				translator.scoreScreenDoneAction();
			}
		});
	}

}
