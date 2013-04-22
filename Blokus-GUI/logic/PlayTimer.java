package logic;

import gui.OrientationPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class PlayTimer {
	
	private static final int DELAY = 1000;
	
	private Timer clock;
	private int timeout;
	private int tickCounter;
	private ActionListener tick;
	private OrientationPanel panel;
	
	public PlayTimer(int defaultTimeout) {
		timeout = defaultTimeout;
		init();
	}
	
	private void init() {
		tickCounter = 1;
		registerTickAction();
		clock = new Timer(DELAY, tick);
	}
	
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	
	public void start() {
		clock.start();
	}
	
	public void stop() {
		clock.stop();
	}
	
	public int getTimeout() {
		return timeout;
	}
	
	public void registerListener(OrientationPanel panel) {
		this.panel = panel;
	}
	
	private void registerTickAction() {
		tick = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tickCounter >= timeout) {
					System.out.println("Timeout!");
					clock.stop();
				} else {
					if(panel != null) {
						panel.timerTick();
					}
					System.out.println("Tick " + tickCounter);
					tickCounter++;
				}
			}
		};
	}

}
