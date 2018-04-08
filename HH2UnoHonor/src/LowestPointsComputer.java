import java.util.ArrayList;
import java.util.Collections;

public class LowestPointsComputer extends Player{
	/**
	 * Creates a Player and gives them a name
	 * @param name
	 */
	public LowestPointsComputer(String name)
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
	protected UnoCard nextCard()
	{
		UnoCard nextCard = null;
		//for now, just grab a random card within the limit
		ArrayList<UnoCard> inRange = new ArrayList<UnoCard>();
		UnoCard top = PlayUno.discards.getTop();
		inRange = validOptions(top);
		if (inRange.isEmpty())
		{
			return null;
		}
		else
		{
			//sort and get lowest pointed card i.e. the first one
			Collections.sort(inRange);
			nextCard =  inRange.get(0);
		}
		if (nextCard != null)
		{
			System.out.println("Computer played: " + nextCard);
		}
		return nextCard;
	}

}
