
public class PlayerFactory {
	public Player makePlayer(int i, String name)
	{
		switch(i)
		{
		case 0:
			return new HumanPlayer(name);
		case 1:
			return new RandomComputer(name);
		case 2:
			return new PointsComputer(name);
		case 3:
			return new LowestPointsComputer(name);
		}
		return null;
	}
}
