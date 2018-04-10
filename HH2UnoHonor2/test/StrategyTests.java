import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;

public class StrategyTests {
	ComputerFactory factory;
	Player cpu;
	ArrayList<UnoCard> myHand;
	DiscardPile discards;
	UnoCard selected;
	UnoCard card;
	@Before
	public void setup()
	{
		factory = new ComputerFactory();
		
		myHand = new ArrayList<UnoCard>();
		discards = new DiscardPile();
	}
	@Test
	public void randomTest() {
		cpu = factory.makePlayer(1, "Computer");
		card = new UnoCard(UnoCard.RED, 1);
		myHand.add(card);
		cpu.setHand(myHand);
		discards.addCard(card);
		selected = cpu.getStrategy().nextCard(discards);
		assertTrue(selected.equals(card));
	}
	@Test
	public void highestPointTest() {
		cpu = factory.makePlayer(2, "Computer");
		card = new UnoCard(UnoCard.RED, 1);
		myHand.add(card);
		card = new UnoCard(UnoCard.RED, 2);
		myHand.add(card);
		card = new UnoCard(UnoCard.RED, 3);
		myHand.add(card);
		cpu.setHand(myHand);
		discards.addCard(card);
		selected = cpu.getStrategy().nextCard(discards);
		assertTrue(selected.equals(card));
	}
	@Test
	public void lowestPointTest() {
		cpu = factory.makePlayer(3, "Computer");
		card = new UnoCard(UnoCard.RED, 1);
		myHand.add(card);
		card = new UnoCard(UnoCard.RED, 2);
		myHand.add(card);
		card = new UnoCard(UnoCard.RED, 3);
		myHand.add(card);
		cpu.setHand(myHand);
		discards.addCard(card);
		selected = cpu.getStrategy().nextCard(discards);
		assertTrue(selected.equals(new UnoCard(UnoCard.RED, 1)));
	}

}
