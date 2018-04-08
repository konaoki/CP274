import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer extends Player{
	public HumanPlayer(String name)
	{
		this.name = name;
		myHand = new ArrayList<UnoCard>();
		lastCardPlayed = null;
		points = 0;
	}
	public UnoCard playCard()
	{
		return null;
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
		ArrayList<UnoCard> options = validOptions(topDiscard);
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
	 * This method finds out what the human wants to do. If they want, they can change strategy or
	 * undo but eventually this method needs to return the next card they want to play 
	 * @return
	 */
	public UnoCard nextCard(DiscardPile discards)
	{
		boolean done = false;
		UnoCard nextCard = null;
		//First, figure out what the player should do. 
		while (!done)
		{
			int choice = playerMenu();
			switch(choice) {
			case PlayUno.CHOOSE_CARD:
				nextCard = chooseCard(discards.getTop());
				done = true;
				break;
			case PlayUno.UNDO_MOVE: //undo
				System.out.println("Not implemented");
				break;
			case PlayUno.CHANGE_STRATEGY: //change strategy
				System.out.println("Not implemented");
				break;	
			}
		}
		return nextCard;

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
}
