package gui;

 

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.*;

import logic.GuiActionTranslator;


public class GameMenuBar extends JMenuBar {
    private static final long serialVersionUID = 1L;
    GuiActionTranslator translator;
    GameFrame parent;

    public GameMenuBar(GameFrame parent, GuiActionTranslator translator) {
        super();
        this.parent = parent;
        this.translator = translator;
        addMenus();
    }
    
    private void addMenus() {
        JMenu singlePlayer = buildSinglePlayerMenu();
        JMenu multiPlayer = buildMultiPlayerMenu();
        JMenu netPlay = buildNetPlayMenu();
        JMenu help = buildHelpMenu();
        
        add(singlePlayer);
        add(multiPlayer);
        add(netPlay);
        add(help);
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
        addMPStartGameListener(startGame);
        JMenuItem options = new JMenuItem("Multiplayer Options");
        addMPOptionsListener(options);
        
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
    
    private JMenu buildHelpMenu() {
        JMenu help = new JMenu("Help");
        
        JMenuItem howToPlay = new JMenuItem("How To Play");
        JMenuItem about = new JMenuItem("About");
        addHelpAboutListener(about);
        addHelpHowToPlayListener(howToPlay);
        
        help.add(howToPlay);
        help.add(about);
        
        return help;
    }
    
    private void addMPStartGameListener(JMenuItem startGame) {
        startGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                translator.multiplayStartGameAction();
                parent.startMPGame();
            }
            
        });
    }
    
    private void addMPOptionsListener(JMenuItem options) {
        options.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                translator.multiplayOptionsAction();
            }
            
        });
    }
    
    private void addHelpAboutListener(JMenuItem about) {
        about.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                String info = "Blokus" + 
                              "\n\nAuthors:\n   Eric Faurie\n   Stephen Felix\n   William Bafia\n   Khoi Nguyen" +
                              "\n\nDate:\n  26 MAR 2013";
                JOptionPane.showMessageDialog(null, info, "About", JOptionPane.INFORMATION_MESSAGE);
            }
            
        });
    }
    
    private void addHelpHowToPlayListener (JMenuItem how2play) {
        how2play.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                String info = "Blokus Info and Rules can be found at:" + 
                              "\n\nhttp://en.wikipedia.org/wiki/Blokus";
                JOptionPane.showMessageDialog(null, info, "How To Play", JOptionPane.INFORMATION_MESSAGE);
            }
            
        });
    }
}
