package tests;

import static org.junit.Assert.*;

import java.awt.Color;

import logic.Lottery;

import org.junit.Test;

public class TestLottery {
	
	@Test
	public void testCorrectDataReceived() {
		Color[] COLORS = {Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE};
		Lottery<Color> color = new Lottery<Color>(COLORS);
		for(int i = 0; i < COLORS.length; i++) {
			Color current = color.getNext();
			if(current == Color.RED || current == Color.GREEN || current == Color.BLUE || current == Color.YELLOW)
				assertTrue(true);
			else
				fail();
		}
	}
	
	@Test
	public void testNoRepetition() {
		Color[] COLORS = {Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE};
		int[] marker = {0, 0, 0, 0};
		Lottery<Color> color = new Lottery<Color>(COLORS);
		for(int i = 0; i < COLORS.length; i++) {
			Color current = color.getNext();
			if(current == Color.RED) {
				marker[0]++;
				if(marker[0] > 1)
					fail();
			} else if(current == Color.GREEN){
				marker[1]++;
				if(marker[0] > 1)
					fail();
			} else if(current == Color.BLUE) {
				marker[2]++;
				if(marker[0] > 1)
					fail();
			} else if(current == Color.YELLOW) {
				marker[3]++;
				if(marker[0] > 1)
					fail();
			} else
				fail();
		}
	}

}
