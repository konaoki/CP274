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


	public static Deck deck;
	public static PlayerFactory factory = new PlayerFactory();
	public static Player human=factory.makePlayer(1,"human");
	public static Player computer=factory.makePlayer(2,"computer");
	public static DiscardPile discards = new DiscardPile();
	public static GameStatusList gameStatusList = new GameStatusList(); 
	public static ConcreteCommand command = new ConcreteCommand();
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

	

	private String determineColor(Player currentPlayer)
	{
		if (currentPlayer == human)
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
	 * This code swaps the players and returns a new current player.
	 * This code assumes that currentPlayer is never created using new - it is just
	 * swapping the addresses, not actually comparing the contents of the classes for equality
	 * @param currentPlayer
	 * @return
	 */
	public Player oppositePlayer(Player currentPlayer)
	{
		if (currentPlayer == human)
		{
			return computer;
		}
		else
		{
			return human;
		}
	}
	

	
	/**
	 * Plays a game of Uno!
	 */
	public void playGame()
	{
		
		human = factory.makePlayer(0,"Human");
		computer = factory.makePlayer(1,"Computer");
		Scanner in = new Scanner(System.in);
		boolean isWinner = false;
		boolean playerOut = false;
		System.out.println("Welcome to Uno!");
		Player currentPlayer;
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

			discards.addCard(deck.pop()); //Need a card to start play

			//Deal initial cards to each player
			human.draw(PlayUno.START_CARDS);
			computer.draw(PlayUno.START_CARDS);

			System.out.println(human);
			System.out.println(computer);

			//While we still have cards left to play...
			while (!playerOut)
			{
				GameStatus gameStatus = new GameStatus(deck, currentPlayer, human, computer, discards);
				gameStatusList.pushStatus(gameStatus);
				deck = gameStatusList.getLatestStatus().getDeck();
				discards = gameStatusList.getLatestStatus().getDiscards();
				currentPlayer = gameStatusList.getLatestStatus().getCurrent();
				human = gameStatusList.getLatestStatus().getHuman();
				computer = gameStatusList.getLatestStatus().getComputer();
				UnoCard topCard = discards.getTop();
				System.out.println("Top of discard pile = " + topCard);

				System.out.println("Current player is " + currentPlayer.getName());
				
				//Start the round - is the top card telling you that you have to do something? Do it!

				//If the top card is Skip or Reverse then we swap players
				if (topCard.skipPlayer())
				{
					System.out.println("Skipping current player");
					currentPlayer = oppositePlayer(currentPlayer);
					gameStatusList.getLatestStatus().setCurrent(currentPlayer);
					System.out.println("Current player is " + currentPlayer.getName());
				}
				if (topCard != currentPlayer.getLastCardPlayed())
				{
					int numToDraw = topCard.numberToDrawFirst();
					if (numToDraw > 0)
					{
						System.out.println(currentPlayer.getName() + " is drawing " + numToDraw + " cards from the deck");
						currentPlayer.draw(numToDraw);
						System.out.println(currentPlayer);
					}
				}

				//Play a card!
				currentPlayer.playCard();
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
						opposite.draw(numToDraw);
						System.out.println(opposite);
					}
					playerOut = true;
				}
				//If the player is not out, swap the players
				else
				{
					currentPlayer = oppositePlayer(currentPlayer);
					gameStatusList.getLatestStatus().setCurrent(currentPlayer);
				}
				
				gameStatus = new GameStatus(deck, currentPlayer, human, computer, discards);
				gameStatusList.pushStatus(gameStatus);
				command.set(gameStatusList);
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
