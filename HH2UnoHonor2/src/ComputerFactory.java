
public class ComputerFactory {
	public Player makePlayer(int i, String name)
	{
		switch(i)
		{
		case 1:
			return new RandomComputerPlayer(name);
		case 2:
			return new HighestPointsComputerPlayer(name);
		case 3:
			return new LowestPointsComputerPlayer(name);
		}
		return null;
	}
	public Player changePlayer(int i, Player current)
	{
		switch(i)
		{
		case 1:
			Player r = new RandomComputerPlayer(current.getName());
			r.setInfo(current.getName(), current.getHand(), current.getLastCardPlayed(), current.getPoints());
			return r;
		case 2:
			Player h = new HighestPointsComputerPlayer(current.getName());
			h.setInfo(current.getName(), current.getHand(), current.getLastCardPlayed(), current.getPoints());
			return h;
		case 3:
			Player l = new LowestPointsComputerPlayer(current.getName());
			l.setInfo(current.getName(), current.getHand(), current.getLastCardPlayed(), current.getPoints());
			return l;
		}
		return null;
	}
}
