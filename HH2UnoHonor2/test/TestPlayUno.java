import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TestPlayUno {

	private Player testPlayer;
	
	@Before
	/**
	 * The setUp method will run fresh before each test
	 * @throws Exception
	 */
	public void setUp() throws Exception {
		testPlayer = new Player("Test");
		testPlayer.replaceCard(new UnoCard(UnoCard.GREEN, UnoCard.MAX));
		testPlayer.replaceCard(new UnoCard(UnoCard.RED, UnoCard.MAX-1));
		testPlayer.replaceCard(new UnoCard(UnoCard.BLUE, 9));
		testPlayer.replaceCard(new UnoCard(UnoCard.RED, 1));
	}

	@Test
	public void inRangeWildColor() {
		PlayUno game = new PlayUno();
		testPlayer.replaceCard(new UnoCard(UnoCard.WILD, UnoCard.WILDCARD));
		testPlayer.replaceCard(new UnoCard(UnoCard.WILD, UnoCard.WILDFOUR));
		ArrayList<UnoCard> range = game.validOptions(testPlayer, new UnoCard(UnoCard.GREEN, UnoCard.WILDCARD));
		assertEquals(3, range.size());
		//Why do these tests fail? What would we have to do for working tests?
		assertTrue(range.contains(new UnoCard(UnoCard.WILD, UnoCard.WILDCARD)));
		assertTrue(range.contains(new UnoCard(UnoCard.WILD, UnoCard.WILDFOUR)));
		assertTrue(range.contains(new UnoCard(UnoCard.GREEN, UnoCard.MAX)));	
	}
	
	@Test
	public void findsColorMatch() {
		PlayUno game = new PlayUno();
		ArrayList<UnoCard> range = game.validOptions(testPlayer, new UnoCard(UnoCard.GREEN, 2));
		assertEquals(1, range.size());
		UnoCard card = range.get(0);
		assertTrue(card.getColor().equals(UnoCard.GREEN) && card.getType() == UnoCard.MAX);		
	}
	
	@Test
	public void findsNumberMatch() {
		PlayUno game = new PlayUno();
		ArrayList<UnoCard> range = game.validOptions(testPlayer, new UnoCard(UnoCard.YELLOW, 9));
		assertEquals(1, range.size());
		UnoCard card = range.get(0);
		assertTrue(card.getColor().equals(UnoCard.BLUE) && card.getType() == 9);	
	}

}
