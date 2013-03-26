package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import logic.GameController;
import logic.GuiActionTranslator;

import org.junit.Test;

public class TestTranslator {
	
	@Test
	public void testGetPlayerColorAsString() {
		GameController game = new GameController();
		GuiActionTranslator translator = new GuiActionTranslator(game);
		for(int i = 0; i < 4; i++) {
			String color = translator.getPlayerColorAsString(i);
			if(color.equals("yellow") || color.equals("red") || color.equals("blue") || color.equals("green"))
				assertTrue(true);
			else
				fail();
		}
	}
	
	@Test
	public void testGetPlayerName() {
		GameController game = new GameController();
		GuiActionTranslator translator = new GuiActionTranslator(game);
		for(int i = 0; i < 4; i++) {
			String name = translator.getPlayerName(i);
			assertEquals(name, "Player " + (i + 1));
		}
	}
	
	@Test
	public void testGetPlayerScore() {
		GameController game = new GameController();
		GuiActionTranslator translator = new GuiActionTranslator(game);
		for(int i = 0; i < 4; i++) {
			assertEquals(translator.getPlayerScore(i), -89);
		}
	}
	
	@Test
	public void testGetBoardMatrix() {
		GameController game = new GameController();
		GuiActionTranslator translator = new GuiActionTranslator(game);
		int[][] matrix = translator.getBoardMatrix();
		for(int i = 0; i < 20; i++) {
			for(int j = 0; j < 20; j++) {
				assertEquals(matrix[i][j], 0);
			}
		}
	}
}
