import java.util.ArrayList;
import java.util.Collections;
/**
 * the class for the strategy where the computer discards randomly
 * @author konao
 *
 */
public class RandomStrategy extends ComputerStrategy {
	/**
	 * constructor
	 * @param player
	 */
	public RandomStrategy(Player player)
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
