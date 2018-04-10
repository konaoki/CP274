/**
 * The game status class holds info about the current game state
 * @author konao
 *
 */
public class GameStatus {
	private Player computer;
	private Player human;
	private Player current;
	private DiscardPile discards;
	private Deck deck;
	private ComputerFactory factory=new ComputerFactory();
	/**
	 * constructor for gamestatus
	 * @param c
	 * @param h
	 * @param curr
	 * @param p
	 * @param d
	 */
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
	/**
	 * copy constructor
	 * @param other
	 */
	public GameStatus(GameStatus other)
	{
		computer = factory.copyPlayer(other.getComputer());
		human=factory.copyPlayer(other.getHuman());
		current=factory.copyPlayer(other.getCurrent());
		deck=new Deck(other.getDeck());
		discards=new DiscardPile(other.getDiscards());
	}
	/**
	 * toString method
	 * @return the string
	 */
	public String toString()
	{
		return "player: "+current.getName()+" || top card: "+discards.getTop()+" || cards: "+current.getHand().size();
	}
	/**
	 * getter
	 * @return computer
	 */
	public Player getComputer() {
		return computer;
	}
	/**
	 * setter
	 * @param computer
	 */
	public void setComputer(Player computer) {
		this.computer = factory.copyPlayer(computer);
	}
	/**
	 * getter
	 * @return human
	 */
	public Player getHuman() {
		return human;
	}
	/**
	 * setter
	 * @param human
	 */
	public void setHuman(Player human) {
		this.human = factory.copyPlayer(human);
	}
	/**
	 * getter
	 * @return current
	 */
	public Player getCurrent() {
		return current;
	}
	/**
	 * setter
	 * @param current
	 */
	public void setCurrent(Player current) {
		this.current = factory.copyPlayer(current);
	}
	/**
	 * getter
	 * @return discards
	 */
	public DiscardPile getDiscards() {
		return discards;
	}
	/**
	 * setter
	 * @param discards
	 */
	public void setDiscards(DiscardPile discards) {
		this.discards = new DiscardPile(discards);
	}
	/**
	 * getter
	 * @return deck
	 */
	public Deck getDeck() {
		return deck;
	}
	/**
	 * setter
	 * @param deck
	 */
	public void setDeck(Deck deck) {
		this.deck = new Deck(deck);
	}
	
	
}
