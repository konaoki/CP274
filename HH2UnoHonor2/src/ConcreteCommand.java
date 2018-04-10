import java.util.Stack;
/**
 * concrete class for command
 * @author konao
 *
 */
public class ConcreteCommand implements Command {
	Stack<GameStatus> gameStatusHistory;
	/**
	 * constructor for command
	 * @param gameStatusHistory
	 */
	public ConcreteCommand(Stack<GameStatus> gameStatusHistory)
	{
		this.gameStatusHistory = gameStatusHistory;
	}
	/**
	 * method for the undo function
	 * @return arraylist of gamestatus's
	 */
	public Stack<GameStatus> undo()
	{
		boolean isHumanTurn=false;
		while(!isHumanTurn)
		{
			if(gameStatusHistory.empty())
			{
				System.out.println("you're at the beggining of the game and can't undo anymore");
				break;
			}
				
			gameStatusHistory.pop();
			if(gameStatusHistory.peek().getCurrent().equals(gameStatusHistory.peek().getHuman()))
			{
				isHumanTurn=true;
			}
			
		}
		if(!gameStatusHistory.empty())
			gameStatusHistory.pop();
		return gameStatusHistory;
	}
}
