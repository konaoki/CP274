
public class GameStatus {
	private Player computer;
	private Player human;
	private Player current;
	private DiscardPile discards;
	private Deck deck;
	private ComputerFactory factory=new ComputerFactory();
	public GameStatus(Player c, Player h, Player curr, DiscardPile p, Deck d)
	{
		if(c!=null)
			computer =factory.copyPlayer(c);
		else
			computer=null;
		if(h!=null)
			human = factory.copyPlayer(h);
		else
			human=null;
		if(current!=null)
			current = factory.copyPlayer(curr);
		else
			current=null;
		if(p!=null)
			discards = new DiscardPile(p);
		else
			discards=null;
		if(d!=null)
			deck=new Deck(d);
		else
			deck=null;
	}
	public GameStatus(GameStatus other)
	{
		computer = factory.copyPlayer(other.getComputer());
		human=factory.copyPlayer(other.getHuman());
		current=factory.copyPlayer(other.getCurrent());
		deck=new Deck(other.getDeck());
		discards=new DiscardPile(other.getDiscards());
	}
	public String toString()
	{
		return "player: "+current.getName()+" || top card: "+discards.getTop()+" || cards: "+current.getHand().size();
	}
	public Player getComputer() {
		return computer;
	}
	public void setComputer(Player computer) {
		this.computer = factory.copyPlayer(computer);
	}
	public Player getHuman() {
		return human;
	}
	public void setHuman(Player human) {
		this.human = factory.copyPlayer(human);
	}
	public Player getCurrent() {
		return current;
	}
	public void setCurrent(Player current) {
		this.current = factory.copyPlayer(current);
	}
	public DiscardPile getDiscards() {
		return discards;
	}
	public void setDiscards(DiscardPile discards) {
		this.discards = new DiscardPile(discards);
	}
	public Deck getDeck() {
		return deck;
	}
	public void setDeck(Deck deck) {
		this.deck = new Deck(deck);
	}
	
	
}
