import java.util.Stack;
public class ConcreteCommand implements Command {
	Stack<GameStatus> gameStatusHistory;
	public ConcreteCommand(Stack<GameStatus> gameStatusHistory)
	{
		this.gameStatusHistory = gameStatusHistory;
	}
	public Stack<GameStatus> undo()
	{
		boolean isHumanTurn=false;
		while(!isHumanTurn)
		{
			
			gameStatusHistory.pop();
			if(gameStatusHistory.peek().getCurrent().equals(gameStatusHistory.peek().getHuman()))
			{
				isHumanTurn=true;
			}
			if(gameStatusHistory.empty())
				break;
		}
		gameStatusHistory.pop();
		return gameStatusHistory;
	}
}
