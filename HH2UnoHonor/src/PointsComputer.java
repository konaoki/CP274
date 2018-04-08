import java.util.ArrayList;
import java.util.Collections;

public class PointsComputer extends Player{
	/**
	 * Creates a Player and gives them a name
	 * @param name
	 */
	public PointsComputer(String name)
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
			//sort and get highest pointed card i.e. last one
			Collections.sort(inRange);
			nextCard =  inRange.get(inRange.size()-1);
		}
		if (nextCard != null)
		{
			System.out.println("Computer played: " + nextCard);
		}
		return nextCard;
	}

}
