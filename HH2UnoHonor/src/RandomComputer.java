import java.util.ArrayList;
import java.util.Collections;

public class RandomComputer extends Player {
	/**
	 * Creates a Player and gives them a name
	 * @param name
	 */
	public RandomComputer(String name)
	{
		this.name = name;
		myHand = new ArrayList<UnoCard>();
		lastCardPlayed = null;
		points = 0;
	}
	/**
	 * Get the next card for the computer player
	 * @return - the playing card
	 */
	protected UnoCard nextCard(DiscardPile discards)
	{
		UnoCard nextCard = null;
		//for now, just grab a random card within the limit
		ArrayList<UnoCard> inRange = new ArrayList<UnoCard>();
		UnoCard top = discards.getTop();
		inRange = validOptions(top);
		if (inRange.isEmpty())
		{
			return null;
		}
		else
		{
			//Shuffle the in range cards and choose one at random
			Collections.shuffle(inRange);
			nextCard =  inRange.get(0);
		}
		if (nextCard != null)
		{
			System.out.println("Computer played: " + nextCard);
		}
		return nextCard;
	}


}
