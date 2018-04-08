import java.util.ArrayList;
import java.util.Collections;

/**
 * Defines the player class. This includes the hand, any books placed on the table, and a name.
 * @author jburge
 *
 */
abstract public class Player {

	protected String name;
	protected ArrayList<UnoCard> myHand;
	protected UnoCard lastCardPlayed;
	protected int points;



	/**
	 * Resets the player for a new hand
	 * @return
	 */
	public void resetForNewHand()
	{
		myHand = new ArrayList<UnoCard>();
		lastCardPlayed = null;
	}

	/**
	 * Returns the name of the player
	 * @return the name
	 */
	public String getName()
	{
		return this.name;
	}


	/**
	 * Returns the player's hand
	 * @return the hand
	 */
	public ArrayList<UnoCard> getHand()
	{
		return myHand;
	}

	/**
	 * Gets the last card played by the player
	 * @return the last card played
	 */
	public UnoCard getLastCardPlayed()
	{
		return lastCardPlayed;
	}

	/**
	 * Returns the number of cards held in the player's hand
	 * @return number of cards
	 */
	public int numCards()
	{
		return myHand.size();
	}


	/**
	 * Add new points to the player's hand
	 */
	public void addPoints(int points)
	{
		this.points += points;
	}

	public void returnPoints(int points)
	{
		this.points -= points;
	}

	public int getPoints()
	{
		return this.points;
	}
	/**
	 * Draws a card from the deck and places it in the player's hand
	 * @param deck the deck of cards
	 * @return the last card dealt
	 */
	public UnoCard draw(Deck deck, DiscardPile discards, int numCards)
	{
		UnoCard cardDealt = null;
		//Make sure there are still cards in the deck!
		if (deck.size() == 0)
		{
			System.out.println("Shuffling discards back into the deck");
			deck.reset(discards);
		}
		for (int i=0; i < numCards; i++)
		{
			cardDealt = deck.pop();
			if (cardDealt != null)
			{
				myHand.add(cardDealt);
			}
		}
		//Sort the hand
		Collections.sort(myHand);
		return cardDealt;

	}

	/**
	 * Puts a card back in the player's hand
	 * @param card the card being added
	 */
	public void replaceCard(UnoCard card)
	{
		myHand.add(card);
	}

	/**
	 * Checks for Uno
	 */
	public boolean uno()
	{
		return myHand.size() == 1;
	}

	public boolean out()
	{
		return myHand.size() == 0;
	}

	public int scoreHand()
	{
		int total = 0;
		for (UnoCard card: myHand)
		{
			total += card.value();
		}
		return total;
	}
	/**
	 * Removes a card from the hand - this is done so it can be added to the discard pile
	 */
	public void removeCard(UnoCard card)
	{
		myHand.remove(card);
		lastCardPlayed = card; //last removed, last played
	}
	/**
	 * Used to create a string representation of the player so it can be printed.
	 */
	public String toString()
	{
		String player = "\n" + name + ":\n";
		player += "Points = " + points + "\n";
		player += "Cards = " + myHand.size() + "\n";
		if (myHand.isEmpty())
		{
			player += "No cards left!";
		}
		else
		{
			player += myHand.toString();
		}
		/*  Useful to look at pile for debugging but not so much for game play...
		player += "\nPile:\n";
		player += myPile.toString();
		 */
		return player;
	}

	public  ArrayList<UnoCard> validOptions(UnoCard topCard)
	{
		ArrayList<UnoCard> inRange = new ArrayList<UnoCard>();
		for (UnoCard card : getHand())
		{
			if (card.playable(topCard))
			{
				inRange.add(card);
			}
		}
		return inRange;
	}

	abstract protected UnoCard nextCard(DiscardPile discards);
	/**
	 * Executes play for a specific player (human or computer)
	 * @return won - return True if this play made the player win
	 */
	public UnoCard playCard(Deck deck, DiscardPile discards)
	{
		UnoCard cardPlayed = null;

		//Find the card we want to play
		cardPlayed = nextCard(discards);
		//Check to see if we could play a card
		if (cardPlayed != null)
		{
			//discards.addCard(cardPlayed);
			removeCard(cardPlayed);
		}
		else
		{
			System.out.println("Could not play a card. Need to draw.");
			UnoCard cardDrawn = draw(deck, discards, 1);	
			System.out.println(getName() + " drew " + cardDrawn);
			if (cardDrawn.playable(discards.getTop()))
			{
				discards.addCard(cardDrawn);
				removeCard(cardDrawn);
				System.out.println(getName() + " playing: " + cardDrawn);
				cardPlayed = cardDrawn;
			}
		}

		System.out.println(this);
		return cardPlayed;
	}


}
