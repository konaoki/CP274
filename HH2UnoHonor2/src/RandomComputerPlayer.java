import java.util.ArrayList;
import java.util.Collections;

public class RandomComputerPlayer extends Player implements ComputerPlayer {
	public RandomComputerPlayer(String name)
	{
		super(name);
	}
	/**
	 * Get the next card for the computer player
	 * @return - the playing card
	 */
	
	@Override
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
	@Override
	public void setInfo(String name, ArrayList<UnoCard> myHand, UnoCard lastCardPlayed, int points)
	{
		this.name=name;
		this.myHand = myHand;
		this.lastCardPlayed = lastCardPlayed;
		this.points = points;
	}
	
	
}
