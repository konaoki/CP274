import java.util.ArrayList;
/**
 * abstract class for strategy
 * @author konao
 *
 */
abstract public class ComputerStrategy{
	Player player;
	/**
	 * abstract method for getting the nextCard
	 * @param discards
	 * @return returns the card seleced by computer depending on strategy
	 */
	abstract public UnoCard nextCard(DiscardPile discards);
	/**
	 * get all valid options player has
	 * @param topCard
	 * @return returns the cards that are valid to play
	 */
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
