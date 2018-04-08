import java.util.*;
public class GameStatusList {
	Stack<GameStatus> gameStatusStack = new Stack<GameStatus>();
	public GameStatus getLatestStatus()
	{
		return gameStatusStack.peek();
	}
	public GameStatus popLastStatus()
	{
		GameStatus gs = gameStatusStack.pop();
		return gs;
	}
	public void pushStatus(GameStatus gs)
	{
		gameStatusStack.push(gs);
	}

}
