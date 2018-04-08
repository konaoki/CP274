
public class ConcreteCommand {
	GameStatusList gameStatusList;
	public void set(GameStatusList g)
	{
		gameStatusList = g;
	}
	public void execute() {
		System.out.println("rolled back to last human turn");
	}
	public void undo()
	{
		boolean isHumanTurn = false;
		while(!isHumanTurn)
		{
			GameStatus gs = gameStatusList.popLastStatus();
			if(gs.getCurrent().equals(gs.getHuman()))
			{
				isHumanTurn=true;
			}
		}
		
		System.out.println("rolling back...");
		execute();
	}
}
