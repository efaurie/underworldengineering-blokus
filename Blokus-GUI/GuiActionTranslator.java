import java.awt.Color;


public class GuiActionTranslator {
	
	GameController game;
	
	GuiActionTranslator(GameController game) {
		this.game = game;
	}
	
	public void multiplayStartGameAction() {
		game.startGame();
	}
	
	public void multiplayOptionsAction() {
		
	}
	
	private Player getPlayerInfo(int playerID) {
		return game.getPlayer(playerID);
	}
	
	public Color getPlayerColor(int playerID) {
		return getPlayerInfo(playerID).getColor();
	}
	
	public String getPlayerName(int playerID) {
		return getPlayerInfo(playerID).getName();
	}
	
	public int getPlayerScore(int playerID) {
		return getPlayerInfo(playerID).getScore();
	}

}
