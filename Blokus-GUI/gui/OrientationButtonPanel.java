package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class OrientationButtonPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	OrientationPanel parent;
	
	JButton rotateCounter;
	JButton rotateClock;
	JButton flip;
	JButton turn;
	
	public OrientationButtonPanel(OrientationPanel parent) {
		this.parent = parent;
		init();
		addContent();
	}
	
	private void init() {
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(600, 35));
		setBackground(Color.BLACK);
	}
	
	private void addContent() {
		rotateCounter = new JButton("Rotate CCW");
		rotateCounter.setFocusPainted(false);
		addRotateCounterAction();
		
		rotateClock = new JButton("Rotate CW");
		rotateClock.setFocusPainted(false);
		addRotateClockAction();
		
		flip = new JButton("Flip");
		flip.setFocusPainted(false);
		addFlipAction();
		
		turn = new JButton("Turn");
		turn.setFocusPainted(false);
		addTurnAction();
		
		add(rotateCounter);
		add(flip);
		add(turn);
		add(rotateClock);
	}
	
	private void addRotateCounterAction() {
		rotateCounter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.rotate(-90);
			}
		});
	}
	
	private void addRotateClockAction() {
		rotateClock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.rotate(90);
			}
		});
	}
	
	private void addFlipAction() {
		flip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.flip();
			}
		});
	}
	
	private void addTurnAction() {
		turn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.turn();
			}
		});
	}

}
