import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

/**
 * This is the main class for a Uno game. See http: https://en.wikipedia.org/wiki/Uno_(card_game)
 * @author jburge
 *
 */
public class PlayUno {

	public static final int START_CARDS = 7; //seven cards in an initial hand
	public static final int DECK_SIZE = UnoCard.MAX;   //set to UnoCard.MAX for a full deck
	public static final  int CHOOSE_CARD = 1;
	public static final int UNDO_MOVE = 2;
	public static final int CHANGE_STRATEGY = 3;
	public static final int WINNING_TOTAL = 100;

	private static final String colors[] = {UnoCard.RED, UnoCard.BLUE, UnoCard.GREEN, UnoCard.YELLOW};

	private ComputerFactory computerFactory = new ComputerFactory();
	private Deck deck;
	private Player human = new Player("Human");
	private Player computer = computerFactory.makePlayer(1, "Computer");
	private Stack<GameStatus> gameStatusHistory = new Stack<GameStatus>();
	private DiscardPile discards = new DiscardPile();
	private GameStatus gameStatus = new GameStatus(computer, human, null, discards, deck);
	
	private Stack<Command> commandHistory = new Stack<Command>(); 
	private UnoCard starter;


	/**
	 * Prints out a string declaring the winner. Exists so we aren't sprinkling the code with
	 * print messages
	 * @param winner - name of the winner
	 */
	private static void declareWinner(String winner)
	{
		System.out.println(winner + " has won!");
	}

	/**
	 * Our main menu for the player options. 
	 * @return the valid menu choice
	 */
	private int playerMenu()
	{
		boolean valid = false;
		Scanner in = new Scanner(System.in);
		int selection = 1;
		while (!valid)
		{
			System.out.println("\nChoose the player option: ");
			System.out.println("1: Continue Play");
			System.out.println("2: Undo the last move");
			System.out.println("3: Change the opponent's strategy");
			System.out.print("> ");
			try {
				selection = in.nextInt();

				in.nextLine();
				if ((selection > 0) && (selection <= 3))
				{
					valid = true;
				}
			}
			//this will catch the mismatch and prevent the error
			catch(InputMismatchException ex)
			{
				//still need to gobble up the end of line
				in.nextLine();
			}
			if (!valid)
			{
				System.out.println("Invalid entry -- enter a number between 1 and 3");
			}
		}
		return selection;
	}
	/**
	 * determines the color
	 * @param currentPlayer
	 * @return the color
	 */
	private String determineColor(Player currentPlayer)
	{
		if (currentPlayer == gameStatus.getHuman())
		{
			Scanner in = new Scanner(System.in);
			boolean valid = false;
			int choice = 0;
			while (true) //Loop until we return a color
			{
				System.out.println("Choose the color: ");
				for (String color: colors)
				{
					choice += 1;
					System.out.println(choice + ":" + color);
				}
				try {
					int selection = in.nextInt();
					in.nextLine();
					if ((selection > 0) && (selection <= colors.length))
					{
						return colors[selection-1];
					}
				}
				//this will catch the mismatch and prevent the error
				catch(InputMismatchException ex)
				{
					//still need to gobble up the end of line
					in.nextLine();
					System.out.println("Invalid selection -- enter a number between 1 and " + colors.length);
				}
			} // end loop until valid choice
		}
		//Computer player - choose randomly! This will need to be replaced with smarter strategies
		else
		{
			Random randomGen = new Random();
			int colorIndex = randomGen.nextInt(colors.length);
			return colors[colorIndex];
		}

	}
	/**
	 * Find out which card the user wants to play/lay down/ ...
	 */
	private UnoCard chooseCard(UnoCard topDiscard)
	{
		int cardWanted = 1;
		boolean valid = false;
		Scanner in = new Scanner(System.in);
		int selection = 1;
		ArrayList<UnoCard> options = gameStatus.getHuman().validOptions(topDiscard);
		int max = options.size();
		if (max == 0)
		{
			return null;
		}
		UnoCard choice = null;
		while (!valid)
		{
			for (UnoCard card : options)
			{
				System.out.println("" + cardWanted + ": " + card.toString() );
				cardWanted++;
			}
			try {
				selection = in.nextInt();
				in.nextLine();
				if ((selection > 0) && (selection <= options.size()))
				{
					choice = options.get(selection-1);
					if (choice.playable(topDiscard))
						valid = true;
					return choice;
				}
				else
				{
					System.out.println("Selection an option between 1 and " + options.size());
				}
			}
			//this will catch the mismatch and prevent the error
			catch(InputMismatchException ex)
			{
				//still need to gobble up the end of line
				in.nextLine();
				System.out.println("Invalid entry -- enter a number between 1 and " + max);
			}
			if (!valid)
			{
				cardWanted = 1;
			}
		}
		return null;
	}
	/**
	 * returns the list of valid cards the player can play
	 * @param currentPlayer
	 * @param topCard
	 * @return the cards
	 */
	public  ArrayList<UnoCard> validOptions(Player currentPlayer, UnoCard topCard)
	{
		ArrayList<UnoCard> inRange = new ArrayList<UnoCard>();
		for (UnoCard card : currentPlayer.getHand())
		{
			if (card.playable(topCard))
			{
				inRange.add(card);
			}
		}
		return inRange;
	}


	/**
	 * This method finds out what the human wants to do. If they want, they can change strategy or
	 * undo but eventually this method needs to return the next card they want to play 
	 * @return
	 */
	public UnoCard nextHumanCard()
	{
		boolean done = false;
		UnoCard nextCard = null;
		//First, figure out what the player should do. 
		while (!done)
		{
			int choice = playerMenu();
			switch(choice) {
			case CHOOSE_CARD:
				nextCard = chooseCard(gameStatus.getDiscards().getTop());
				done = true;
				break;
			case UNDO_MOVE:
				System.out.println("rolling back...");
				Command com = new ConcreteCommand(gameStatusHistory);
				gameStatusHistory = com.undo();
				if(!gameStatusHistory.empty())
					gameStatus = gameStatusHistory.peek();
				System.out.println("----------------------");
				System.out.println("saved status history");
				ArrayList<GameStatus> list2 = new ArrayList(gameStatusHistory);
				for(int i=0; i<list2.size(); i++)
				{
					System.out.println(list2.get(i));
				}
				
				System.out.println("----------------------");
				break;
			case CHANGE_STRATEGY:
				//copies over info from the current computer and pastes it to the new computer instance
				Player cpu = computerFactory.changePlayer(chooseStrategy(), gameStatus.getComputer());
				gameStatus.setComputer(cpu);
				break;	
			}
		}
		return nextCard;

	}
	/**
	 * Opens a scanner and asks for user input for strategy choice
	 * @return the integer id of the strategy chosen
	 */
	private int chooseStrategy()
	{
		System.out.println("Choose strategy 1, 2, or 3");
		System.out.println("1: Computer plays random");
		System.out.println("2: Computer plays the higest pointed card");
		System.out.println("3: Computer plays the lowest pointed card");
		Scanner in = new Scanner(System.in);
		int strategy = in.nextInt();
		in.nextLine();
		return strategy;

	}

	/**
	 * Executes play for a specific player (human or computer)
	 * @return won - return True if this play made the player win
	 */
	public UnoCard playCard(Player currentPlayer)
	{
		UnoCard cardPlayed = null;
		DiscardPile discards = gameStatus.getDiscards();
		//Find the card we want to play
		if (currentPlayer.equals(gameStatus.getComputer()))
		{
			cardPlayed = gameStatus.getComputer().getStrategy().nextCard(discards);
		}
		else
		{
			cardPlayed = nextHumanCard();
		}
		//Check to see if we could play a card
		if (cardPlayed != null)
		{
			discards.addCard(cardPlayed);
			//System.out.println("card added!! "+cardPlayed+" check status: "+gameStatus.getDiscards().getTop());
			currentPlayer.removeCard(cardPlayed);
			System.out.println(cardPlayed+" was removed from "+currentPlayer.getName()+"'s hand");
		}
		else
		{
			System.out.println("Could not play a card. Need to draw.");
			UnoCard cardDrawn = drawCards(currentPlayer, 1);	
			System.out.println(currentPlayer.getName() + " drew " + cardDrawn);
			if (cardDrawn.playable(discards.getTop()))
			{
				discards.addCard(cardDrawn);
				currentPlayer.removeCard(cardDrawn);
				System.out.println(currentPlayer.getName() + " playing: " + cardDrawn);
				cardPlayed = cardDrawn;
			}
		}
		gameStatus.setCurrent(currentPlayer);
		gameStatus.setDiscards(discards);
		return cardPlayed;



	}


	/**
	 * If we have run out of cards to draw but players still have cards in their hands we need to
	 * return the discards
	 */
	public void resetDeck()
	{
		DiscardPile discards = gameStatus.getDiscards();
		Deck deck = gameStatus.getDeck();
		UnoCard topCard = discards.draw();
		ArrayList<UnoCard> ourDiscards = discards.getDiscards();
		deck.returnCards(ourDiscards);
		deck.shuffle();
		discards = new DiscardPile();
		discards.addCard(topCard);
		gameStatus.setDeck(deck);
		gameStatus.setDiscards(discards);
	}

	/**
	 * This code swaps the players and returns a new current player.
	 * This code assumes that currentPlayer is never created using new - it is just
	 * swapping the addresses, not actually comparing the contents of the classes for equality
	 * @param currentPlayer
	 * @return
	 */
	public Player oppositePlayer(Player currentPlayer)
	{
		if (currentPlayer.equals(gameStatus.getHuman()))
		{
			return gameStatus.getComputer();
		}
		else
		{
			return gameStatus.getHuman();
		}
	}
	/**
	 * draws the card from a player
	 * @param currentPlayer
	 * @param numCards
	 * @return the last card drawn
	 */
	public UnoCard drawCards(Player currentPlayer, int numCards)
	{
		UnoCard lastDrawn = null;
		for (int i = 0; i < numCards; i++)
		{
			//Make sure there are still cards in the deck!
			if (gameStatus.getDeck().size() == 0)
			{
				System.out.println("Shuffling discards back into the deck");
				resetDeck();
			}
			lastDrawn = currentPlayer.draw(gameStatus.getDeck(), 1);
		}
		return lastDrawn;
	}
	/**
	 * Plays a game of Uno!
	 */
	public void playGame()
	{
		Player computer = this.computer;
		Player human = this.human;
		Player currentPlayer = null;
		DiscardPile discards = this.discards;
		Deck deck = this.deck;


		Scanner in = new Scanner(System.in);
		boolean isWinner = false;
		boolean playerOut = false;
		System.out.println("Welcome to Uno!");

		boolean handOver;

		Random randomGen = new Random();
		//Figure out who deals first - player or computer
		if (randomGen.nextInt(2) == 0)
		{
			currentPlayer = computer;

		}
		else
		{
			currentPlayer = human;
		}

		System.out.println("\nFirst Player is " + currentPlayer.getName());

		//Outer loop for the game - need to deal out new hands if we haven't played long enough to hit 500
		while (!isWinner)
		{

			//Dealing out new hands - always start with a freshly shuffled deck
			System.out.println("\nDealing out new hands");
			handOver = false;
			deck = new Deck(DECK_SIZE);
			human.resetForNewHand();
			computer.resetForNewHand();
			discards = new DiscardPile();

			discards.addCard(deck.draw()); //Need a card to start play

			//Deal initial cards to each player
			human.draw(deck, PlayUno.START_CARDS);
			computer.draw(deck, PlayUno.START_CARDS);

			//set up is over so saving it to gameStatus
			gameStatus.setComputer(computer);
			gameStatus.setHuman(human);
			gameStatus.setCurrent(currentPlayer);
			gameStatus.setDeck(deck);
			gameStatus.setDiscards(discards);



			//While we still have cards left to play...
			while (!playerOut)
			{

				//make sure to start the round with the saved info from gameStatus
				computer = gameStatus.getComputer();
				human = gameStatus.getHuman();
				discards = gameStatus.getDiscards();
				deck = gameStatus.getDeck();

						System.out.println("*******************");
				UnoCard topCard = discards.getTop();
				System.out.println("Top of discard pile = " + topCard);

				System.out.println("Current player is " + currentPlayer.getName());
				System.out.println("player hand: "+currentPlayer.getHand());
				//Start the round - is the top card telling you that you have to do something? Do it!

				//If the top card is Skip or Reverse then we swap players
				if (topCard.skipPlayer())
				{
					System.out.println("Skipping current player");
					currentPlayer = oppositePlayer(currentPlayer);
					System.out.println("Current player is " + currentPlayer.getName());
				}
				if (topCard != currentPlayer.getLastCardPlayed())
				{
					int numToDraw = topCard.numberToDrawFirst();
					if (numToDraw > 0)
					{
						System.out.println(currentPlayer.getName() + " is drawing " + numToDraw + " cards from the deck");
						drawCards(currentPlayer, numToDraw);
						System.out.println(currentPlayer);
					}
				}
				
				gameStatus.setCurrent(currentPlayer);
				//Play a card!
				playCard(currentPlayer);

				//just to update info again
				computer = gameStatus.getComputer();
				human = gameStatus.getHuman();
				currentPlayer = gameStatus.getCurrent();
				discards = gameStatus.getDiscards();
				deck = gameStatus.getDeck();

				//If the top card is wild, figure out the color	
				if (discards.getTop().isWild())
				{
					if (!currentPlayer.out())
					{
						String color = determineColor(currentPlayer);
						discards.getTop().setColor(color);
						System.out.println("Color is " + color);
					}
				}
				if (currentPlayer.uno())
				{
					System.out.println("\n****" + currentPlayer.getName() + " Uno!****");
				}
				if (currentPlayer.out())
				{
					System.out.println("\n**** " + currentPlayer.getName() + " is out of cards!****");
					int numToDraw = topCard.numberToDrawFirst();
					if (numToDraw > 0)
					{
						Player opposite = oppositePlayer(currentPlayer);
						System.out.println(opposite.getName() + " is drawing " + numToDraw + " cards from the deck");
						drawCards(opposite, numToDraw);
						System.out.println(opposite);
					}
					playerOut = true;
				}
				//If the player is not out, swap the players
				else
				{
					
					currentPlayer = oppositePlayer(currentPlayer);
					
				}
				System.out.println("----------------------");
				System.out.println("saving status...");
				//adds the status to history
				gameStatusHistory.push(new GameStatus(gameStatus));
				
				ArrayList<GameStatus> list2 = new ArrayList(gameStatusHistory);
				for(int i=0; i<list2.size(); i++)
				{
					System.out.println(list2.get(i));
				}
				
				System.out.println("----------------------");
			}

			//Count up the points in the opponent's hand 
			int points = oppositePlayer(currentPlayer).scoreHand();
			currentPlayer.addPoints(points);
			System.out.println(currentPlayer.getName() + " gets: " + points);
			playerOut = false;
			if (currentPlayer.getPoints() >= WINNING_TOTAL)
			{
				isWinner = true;
				declareWinner(currentPlayer.getName());
			}
		}

	}
	/**
	 * Main Program
	 * @param args
	 */
	public static void main(String[] args) {

		PlayUno unoGame = new PlayUno();
		unoGame.playGame();
	}


}
