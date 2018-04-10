import java.util.ArrayList;

abstract public class ComputerStrategy{
	Player player;
	abstract public UnoCard nextCard(DiscardPile discards);
	protected ArrayList<UnoCard> validOptions(UnoCard topCard)
	{
		ArrayList<UnoCard> inRange = new ArrayList<UnoCard>();
		for (UnoCard card : player.getHand())
		{
			if (card.playable(topCard))
			{
				inRange.add(card);
			}
		}
		return inRange;
	}
}
