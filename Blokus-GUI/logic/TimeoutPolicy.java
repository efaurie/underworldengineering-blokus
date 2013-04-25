package logic;

public class TimeoutPolicy {
	
	private boolean kickOnTimeout;
	private GameController game;
	private GuiActionTranslator gui;
	Integer[] playerIDs;
	private Lottery<Integer> playerReplacement;
	
	
	public TimeoutPolicy(GameController game, GuiActionTranslator gui) {
		this.game = game;
		this.gui = gui;
		playerIDs = new Integer[4];
		for(int i = 0; i < 4; i++) {
			playerIDs[i] = new Integer(i);
		}
		playerReplacement = new Lottery<Integer>(playerIDs);
		kickOnTimeout = false;
	}
	
	public void timeoutAction() {
		if(kickOnTimeout) {
			int replacement = gui.getCurrentPlayer();
			while(replacement == gui.getCurrentPlayer())
				replacement = playerReplacement.getNext();
			game.playerKicked(replacement);
			gui.resetTurnAction();
			if(game.getPlayersRemaining() <= 1) {
				gui.endGame();
			}
		} else {
			gui.passAction();
		}
	}
	
	public void setKickOnTimeout(boolean isKicked) {
		kickOnTimeout = isKicked;
	}
	
	

}
