import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class GameMenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;
	GameData game;

	public GameMenuBar(GameData game) {
		super();
		this.game = game;
		addMenus();
	}
	
	private void addMenus() {
		JMenu singlePlayer = buildSinglePlayerMenu();
		JMenu multiPlayer = buildMultiPlayerMenu();
		JMenu netPlay = buildNetPlayMenu();
		
		add(singlePlayer);
		add(multiPlayer);
		add(netPlay);
	}
	
	private JMenu buildSinglePlayerMenu() {
		JMenu singlePlayer = new JMenu("Single Player");
		
		JMenuItem startGame = new JMenuItem("Start Game");
		JMenuItem options = new JMenuItem("Single Player Options");
		
		singlePlayer.add(startGame);
		singlePlayer.add(options);
		
		return singlePlayer;
	}
	
	private JMenu buildMultiPlayerMenu() {
		JMenu multiPlayer = new JMenu("Multiplayer");
		
		JMenuItem startGame = new JMenuItem("Start Game");
		addStartGameListener(startGame);
		JMenuItem options = new JMenuItem("Multiplayer Options");
		addOptionsListener(options);
		
		multiPlayer.add(startGame);
		multiPlayer.add(options);
		
		return multiPlayer;
	}
	
	private JMenu buildNetPlayMenu() {
		JMenu netPlay = new JMenu("Net Play");
		
		JMenuItem hostGame = new JMenuItem("Host Game");
		JMenuItem joinGame = new JMenuItem("Join Game");
		JMenuItem options = new JMenuItem("Netplay Options");
		
		netPlay.add(hostGame);
		netPlay.add(joinGame);
		netPlay.add(options);
		
		return netPlay;
	}
	
	private void addStartGameListener(JMenuItem startGame) {
		startGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Hitting start!");
				game.start();
			}
			
		});
	}
	
	private void addOptionsListener(JMenuItem options) {
		options.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
			
		});
	}
}
