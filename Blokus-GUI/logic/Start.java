package logic;
import gui.GameFrame;


public class Start {
	
	public static void main(String[] args) {
		GameController game = new GameController();
		GuiActionTranslator translator = new GuiActionTranslator(game);
		new GameFrame(translator);
	}

}
