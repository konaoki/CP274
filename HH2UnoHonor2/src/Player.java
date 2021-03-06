import java.util.ArrayList;
import java.util.Collections;

/**
 * Defines the player class. This includes the hand, any books placed on the table, and a name.
 * @author jburge
 *
 */
public class Player {

	protected String name;
	protected ArrayList<UnoCard> myHand;
	protected UnoCard lastCardPlayed;
	protected int points;
	protected ComputerStrategy strategy;

	/**
	 * Creates a Player and gives them a name
	 * @param name
	 */
	public Player(String name)
	{
		this.name = name;
		myHand = new ArrayList<UnoCard>();
		lastCardPlayed = null;
		points = 0;
		this.strategy=null;
	}
	public Player(Player other)
	{
		name = other.getName();
		myHand = other.getHand();
		lastCardPlayed = other.getLastCardPlayed();
		points = other.getPoints();
		strategy = other.getStrategy();
	}
	/**
	 * sets the strategy of this player
	 * @param strategy
	 */
	public void setStrategy(ComputerStrategy strategy)
	{
		this.strategy = strategy;
	}
	/**
	 * gets the strategy
	 * @return the strategy
	 */
	public ComputerStrategy getStrategy()
	{
		return this.strategy;
	}
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
	/**
	 * returns the points
	 * @param points
	 */
	public void returnPoints(int points)
	{
		this.points -= points;
	}
	/**
	 * getter
	 * @return the points
	 */
	public int getPoints()
	{
		return this.points;
	}
	/**
	 * Draws a card from the deck and places it in the player's hand
	 * @param deck the deck of cards
	 * @return the last card dealt
	 */
	public UnoCard draw(Deck deck, int numCards)
	{
		UnoCard cardDealt = null;
		for (int i=0; i < numCards; i++)
		{
			cardDealt = deck.draw();
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
	/**
	 * if player is out of cards
	 * @return true is player is out
	 */
	public boolean out()
	{
		return myHand.size() == 0;
	}
	/**
	 * the score
	 * @return the score
	 */
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
	
	/**
	 * returns list of cards that are valid to play for this player
	 * @param topCard
	 * @return
	 */
	public ArrayList<UnoCard> validOptions(UnoCard topCard)
	{
		ArrayList<UnoCard> inRange = new ArrayList<UnoCard>();
		for (UnoCard card : this.getHand())
		{
			if (card.playable(topCard))
			{
				inRange.add(card);
			}
		}
		return inRange;
	}
	/**
	 * sets all the info for this player
	 * @param name
	 * @param myHand
	 * @param lastCardPlayed
	 * @param points
	 */
	public void setInfo(String name, ArrayList<UnoCard> myHand, UnoCard lastCardPlayed, int points)
	{
		this.name=name;
		this.myHand = myHand;
		this.lastCardPlayed = lastCardPlayed;
		this.points = points;
	}
	/**
	 * sets the hand for this player
	 * @param myHand
	 */
	public void setHand(ArrayList<UnoCard> myHand)
	{
		this.myHand = myHand;
	}
	/**
	 * checks for equality between players
	 * @param other
	 * @return
	 */
	public boolean equals(Player other)
	{
		if(other.getName().equals(this.getName()))
		{
			return true;
		}
		return false;
	}


}
