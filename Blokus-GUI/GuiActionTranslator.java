
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

}
