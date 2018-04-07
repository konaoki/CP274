import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestUnoCard {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testWildDrawFourCount() {
		UnoCard card = new UnoCard(UnoCard.WILD, UnoCard.WILDFOUR);
		assertEquals(4, card.numberToDrawFirst());
	}
	
	@Test
	public void testDrawTwoCount() {
		UnoCard card = new UnoCard(UnoCard.BLUE, 10);
		assertEquals(2, card.numberToDrawFirst());
	}
	
	@Test
	public void testDrawRegularWild() {
		UnoCard card = new UnoCard(UnoCard.WILD, UnoCard.WILDCARD);
		assertEquals(0, card.numberToDrawFirst());
	}
	
	@Test
	public void testDrawNumberCardHigh() {
		UnoCard card = new UnoCard(UnoCard.GREEN, UnoCard.MAX);
		assertEquals(0, card.numberToDrawFirst());
	}
	
	@Test
	public void testDrawNumberCardLow() {
		UnoCard card = new UnoCard(UnoCard.GREEN, UnoCard.MIN);
		assertEquals(0, card.numberToDrawFirst());
	}
	
	@Test
	public void testPlayableWildDiscardColor() {
		UnoCard discard = new UnoCard(UnoCard.GREEN, UnoCard.WILDCARD);
		UnoCard card = new UnoCard(UnoCard.GREEN, UnoCard.MIN);
		assertTrue(card.playable(discard));
	}
	
	@Test
	public void testNotPlayableWildDiscardColor() {
		UnoCard discard = new UnoCard(UnoCard.GREEN, UnoCard.WILDCARD);
		UnoCard card = new UnoCard(UnoCard.BLUE, UnoCard.MIN);
		assertFalse(card.playable(discard));
	}
	
	@Test
	public void testPlayableWildOnWild() {
		UnoCard discard = new UnoCard(UnoCard.GREEN, UnoCard.WILDCARD);
		UnoCard card = new UnoCard(UnoCard.WILD, UnoCard.WILDFOUR);
		assertTrue(card.playable(discard));
	}
	
	@Test
	public void testPlayableNumberMatch() {
		UnoCard discard = new UnoCard(UnoCard.GREEN, UnoCard.MAX);
		UnoCard card = new UnoCard(UnoCard.BLUE, UnoCard.MAX);
		assertTrue(card.playable(discard));
	}
	
	@Test
	public void testNotPlayableNumberNotMatch() {
		UnoCard discard = new UnoCard(UnoCard.GREEN, UnoCard.MAX);
		UnoCard card = new UnoCard(UnoCard.BLUE, UnoCard.MAX-1);
		assertFalse(card.playable(discard));
	}
	
	@Test
	public void testPlayableColorMatchNumberNotMatch() {
		UnoCard discard = new UnoCard(UnoCard.BLUE, UnoCard.MAX);
		UnoCard card = new UnoCard(UnoCard.BLUE, UnoCard.MAX-1);
		assertTrue(card.playable(discard));
	}

}
