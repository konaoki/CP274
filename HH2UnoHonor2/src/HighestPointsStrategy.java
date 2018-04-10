import java.util.ArrayList;
import java.util.Collections;

/**
 * the class for the strategy where the computer discards the highest pointed card in his hand
 * @author konao
 *
 */
public class HighestPointsStrategy extends ComputerStrategy  {
	/**
	 * constructor
	 * @param player
	 */
	public HighestPointsStrategy(Player player)
	{
		this.player=player;
	}
	/**
	 * derives the next card the computer will play according to this strategy
	 * @return the unocard
	 */
	public UnoCard nextCard(DiscardPile discards)
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
