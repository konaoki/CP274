
public class GameStatus {
	private Deck deck;
	private Player human;
	private Player computer;
	private DiscardPile discards;
	private Player current;
	public GameStatus(Deck d, Player cu, Player h, Player c, DiscardPile p)
	{
		deck=d;
		human=h;
		computer=c;
		discards=p;
		current =cu;
	}
	public String toString()
	{
		return null;
	}
	public Deck getDeck()
	{
		return deck;
	}
	public Player getHuman()
	{
		return human;
	}
	public Player getComputer()
	{
		return computer;
	}
	public DiscardPile getDiscards()
	{
		return discards;
	}
	public Player getCurrent()
	{
		return current;
	}
	public void setCurrent(Player c)
	{
		current = c;
	}
}
