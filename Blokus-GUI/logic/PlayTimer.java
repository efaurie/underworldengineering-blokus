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
		clock.restart();
	}
	
	public void stop() {
		clock.stop();
		tickCounter = 0;
	}
	
	public int getTimeout() {
		return timeout;
	}
	
	public void registerPanelListener(OrientationPanel panel) {
		this.panel = panel;
	}
	
	private void registerTickAction() {
		tick = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tickCounter >= timeout) {
					stop();
					panel.timerTimeout();
				} else {
					if(panel != null) {
						panel.timerTick();
					}
					tickCounter++;
				}
			}
		};
	}

}
