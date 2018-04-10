
/**
 * This class defines an UNO Card
 * @author jburge
 *
 */
public class UnoCard implements Comparable {

	public static final int MIN = 0;
	public static final int MAX = 12; 
	public static final int WILDCOUNT = 4;
	public static final int WILDCARD = 13;
	public static final int WILDFOUR = 14;
	public static final int INVALID = -1;

	public static final String RED = "Red";
	public static final String GREEN = "Green";
	public static final String YELLOW = "Yellow";
	public static final String BLUE = "Blue";
	public static final String WILD = "Wild";

	private static final String types[] = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
			"Nine", "Draw Two", "Skip", "Reverse", "Wild", "Wild Draw 4"};
	private static final int value[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 20, 20, 20, 20, 50};

	private String color;
	private int type;
	private int id;
	private static int counter;

	/**
	 * The constructor for the UnoCard
	 * @param color - the color
	 * @param type - the type
	 */
	public UnoCard(String color, int type) {
		super();
		this.color = color;
		this.type = type;
		this.id = counter;
		this.counter += 1;
	}
	/**
	 * copy constructor
	 * @param other
	 */
	public UnoCard(UnoCard other)
	{
		this.color = other.getColor();
		this.type = other.getType();
	}
	/**
	 * gets the name of the card
	 * @param value
	 * @return the name as String
	 */
	public static String getName(int value) {
		return types[value];
	}
	
	/**
	 * Converts from the name to the type
	 * @param name the name of the card
	 * @return the type
	 */
	public static int determineType(String name)
	{
		for (int i=1; i < types.length; i ++)
		{
			if (name.toLowerCase().contains(types[i].toLowerCase()))
			{
				return i;
			}	
		}
		return INVALID;
	}
	/**
	 * gets color
	 * @return the color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * setter
	 * @param color
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * getter
	 * @return type
	 */
	public int getType() {
		return type;
	}
	/**
	 * setter
	 * @param type
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * Used to check if this card is telling us to skip the next player
	 * @return
	 */
	public boolean skipPlayer()
	{
		String action = types[this.type];
		return (action.equals("Skip") || action.equals("Reverse"));
	}

	/**
	 * Returns the number of cards that should be drawn if this card is the top of the discards
	 * @return the number of cards to draw
	 */
	public int numberToDrawFirst()
	{
		String action = types[this.type];
		if (action.equals("Draw Two"))
		{
			return 2;
		}
		else if (action.equals("Wild Draw 4"))
		{
			return 4;
		}
		return 0;

	}
	/**
	 * This assumes that an Ace will have a value of 1
	 * @return
	 */
	public int value()
	{
		return value[type];
	}

	/**
	 * Return true if the card is wild
	 * @return true if a wild card
	 */
	public boolean isWild()
	{
		return this.color == UnoCard.WILD;
	}

	/**
	 * Checks to see if the current (this) Uno card is playable if the card passed in is 
	 * on the top of the discard pile
	 * @param discard - the top of the discard pile
	 * @return true if playable
	 */
	public boolean playable(UnoCard discard)
	{
		if (discard.getColor().equals(this.getColor()))
		{
			return true;
		}
		else if (this.equalsType(discard.type))
		{
			return true;
		}
		else if (this.isWild() || discard.isWild()) //can always play a wild card
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Resets the color of a card - should be done if the card is returned to the deck or hand
	 */
	public void resetCard() {
		if (equalsType(UnoCard.WILDCARD) || equalsType(UnoCard.WILDFOUR))
		{
			this.color = UnoCard.WILD; //reset the color since it is back to being a normal wildcard
		}
	}
	/**
	 * checks for type equality
	 * @param type
	 * @return true is types are same
	 */
	public boolean equalsType(int type) {
		return this.type == type;
	}

	/**
	 * This is a PROPER equals method used to compare two Uno cards
	 */
	public boolean equals(Object o) {
		if (o == null)
		{
			return false;
		}
		if (o.getClass() == this.getClass())
		{
			UnoCard card = (UnoCard) o;
			if (card.color.equals(this.color) && (card.type == this.type) && (card.id == this.id))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Used to sort Uno Cards
	 */
	public int compareTo(Object o) {
		if ((o == null) || (!(o instanceof UnoCard)))
		{
			return -100; //kludge
		}
		UnoCard card = (UnoCard) o;
		if (this.getType() < card.getType())
		{
			return -1;
		}
		else if (this.getType() == card.getType())
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}

	/**
	 * Returns a String representation of the card
	 */
	public String toString()
	{
		return color + " " + types[type];
	}

}
