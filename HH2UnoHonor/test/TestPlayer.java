import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestPlayer {

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void scoreSingleWildFour() {
		Player testPlayer = new Player("Test");
		testPlayer.replaceCard(new UnoCard(UnoCard.WILD, UnoCard.WILDFOUR));
		assertEquals(50, testPlayer.scoreHand());	
	}
	
	@Test
	public void scoreSingleWild() {
		Player testPlayer = new Player("Test");
		testPlayer.replaceCard(new UnoCard(UnoCard.WILD, UnoCard.WILDCARD));
		assertEquals(20, testPlayer.scoreHand());
	}
	
	@Test
	public void scoreSingleZero() {
		Player testPlayer = new Player("Test");
		testPlayer.replaceCard(new UnoCard(UnoCard.BLUE, UnoCard.MIN));
		assertEquals(0, testPlayer.scoreHand());
	}
	
	@Test
	public void scoreSingleDrawTwo() {
		Player testPlayer = new Player("Test");
		testPlayer.replaceCard(new UnoCard(UnoCard.RED, 10));
		assertEquals(20, testPlayer.scoreHand());
	}
	
	@Test
	public void scoreMultiples() {
		Player testPlayer = new Player("Test");
		testPlayer.replaceCard(new UnoCard(UnoCard.GREEN, UnoCard.MAX));
		testPlayer.replaceCard(new UnoCard(UnoCard.RED, UnoCard.MAX-1));
		testPlayer.replaceCard(new UnoCard(UnoCard.WILD, UnoCard.WILDCARD));
		testPlayer.replaceCard(new UnoCard(UnoCard.WILD, UnoCard.WILDFOUR));
		testPlayer.replaceCard(new UnoCard(UnoCard.BLUE, 9));
		testPlayer.replaceCard(new UnoCard(UnoCard.YELLOW, 1));
		assertEquals(120, testPlayer.scoreHand());
	}

}
