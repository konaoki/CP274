import java.util.ArrayList;
import java.util.Collections;

/**
 * Defines a deck of Uno cards. Allows decks to be smaller for testing purposes.
 * @author jburge
 *
 */
public class Deck {
	private ArrayList<UnoCard> deck;
	private int deckSize;

	/**
	 * Constructor
	 * @param deckSize - used to create smaller than regulation decks
	 */
	public Deck(int deckSize)
	{
		deck = new ArrayList<UnoCard>();
		this.deckSize = deckSize;
		this.initializeDeck();
	}

	/**
	 * Used to get how many cards are in the deck
	 * @return
	 */
	public int size()
	{
		return deck.size();
	}

	/**
	 * Used to add a suit of cards to the deck
	 * @param suit
	 */
	public void addSuit(String suit)
	{
		//Normally we'd use PlayingCard.MAX but we want the option of smaller decks
		for (int i = UnoCard.MIN; i <= deckSize; i++)
		{
			//Add two of each type unless it's a zero
			UnoCard newCard = new UnoCard(suit, i);
			deck.add(newCard);
			if (i > UnoCard.MIN)
			{
				newCard = new UnoCard(suit, i);
				deck.add(newCard);
			}
		}
	}

	/**
	 * Add the wild cards to the deck
	 */
	public void addWilds()
	{
		for (int i = 0; i < UnoCard.WILDCOUNT; i++)
		{
			UnoCard newCard = new UnoCard(UnoCard.WILD, UnoCard.WILDCARD );
			deck.add(newCard);
			newCard = new UnoCard(UnoCard.WILD, UnoCard.WILDFOUR);
			deck.add(newCard);
		}
	}

	/**
	 * Initializes a deck of cards with all four suits
	 */
	public  void initializeDeck()
	{
		addSuit(UnoCard.RED);
		addSuit(UnoCard.BLUE);
		addSuit(UnoCard.GREEN);
		addSuit(UnoCard.YELLOW);
		addWilds();
		Collections.shuffle(deck);
	}

	/**
	 * Returns a card to the deck - used when undoing commands
	 * @param card - the card that we are returning
	 */
	public void returnCard(UnoCard card)
	{
		card.resetCard();
		deck.add(card);
	}

	/**
	 * Return a list of cards to the deck. Used when we are creating a new deck from the discards
	 * @param cards
	 */
	public void returnCards(ArrayList<UnoCard> cards)
	{
		for (UnoCard card: cards)
		{
			card.resetCard();
		}
		deck.addAll(cards);
	}

	/**
	 * Shuffle the deck of cards
	 */
	public void shuffle()
	{
		Collections.shuffle(deck);
	}
	/**
	 * Draws a card from the top of the deck. Assumes the deck has been shuffled!
	 * @return - the card that has been drawn
	 */
	public UnoCard pop()
	{
		if (deck.size() > 0)
		{
			UnoCard card = deck.get(0);
			deck.remove(0);
			return card;
		}
		else
		{
			return null;
		}

	}
	
	public void reset(DiscardPile discards)
	{
		UnoCard topCard = discards.pop();
		ArrayList<UnoCard> ourDiscards = discards.getDiscards();
		returnCards(ourDiscards);
		shuffle();
		discards = new DiscardPile();
		discards.addCard(topCard);
	}
}
