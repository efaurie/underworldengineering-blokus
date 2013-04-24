package logic;

public class TimeoutPolicy {
	
	private boolean kickOnTimeout = false;
	private GameController game;
	private GuiActionTranslator gui;
	Integer[] playerIDs;
	private Lottery<Integer> playerReplacement;
	
	
	public TimeoutPolicy(GameController game, GuiActionTranslator gui) {
		this.game = game;
		this.gui = gui;
		resetLottery();
		kickOnTimeout = false;
	}
	
	public void timeoutAction() {
		if(kickOnTimeout) {
			game.playerKicked(playerReplacement.getNext());
		} else {
			gui.passAction();
		}
	}
	
	private void resetLottery() {
		playerIDs = new Integer[game.getPlayersRemaining()];
		for(int i = 0; i < game.getPlayersRemaining(); i++) {
			playerIDs[i] = i+1;
		}
		playerReplacement = new Lottery<Integer>(playerIDs);
	}
	
	public void setKickOnTimeout(boolean isKicked) {
		kickOnTimeout = isKicked;
	}
	
	

}
