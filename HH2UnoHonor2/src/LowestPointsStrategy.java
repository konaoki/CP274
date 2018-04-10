import java.util.ArrayList;
import java.util.Collections;

public class LowestPointsStrategy extends ComputerStrategy  {
	public LowestPointsStrategy(Player player)
	{
		this.player=player;
	}
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
			nextCard =  inRange.get(0);
		}
		if (nextCard != null)
		{
			System.out.println("Computer played: " + nextCard);
		}
		return nextCard;
	}
}
