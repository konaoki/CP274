import java.util.ArrayList;

public class ComputerFactory {
	public Player makePlayer(int i, String name)
	{
		
		switch(i)
		{
		case 1:
			Player p = new Player(name);
			ComputerStrategy strat = new RandomStrategy(p);
			p.setStrategy(strat);
			return p;
		case 2:
			p = new Player(name);
			strat = new HighestPointsStrategy(p);
			p.setStrategy(strat);
			return p;
		case 3:
			p = new Player(name);
			strat = new LowestPointsStrategy(p);
			p.setStrategy(strat);
			return p;
		}
		return null;
	}
	public Player changePlayer(int i, Player current)
	{
		ArrayList<UnoCard> hand = new ArrayList();
		hand.addAll(current.getHand());
		switch(i)
		{
		case 1:
			Player p = copyPlayer(current);
			ComputerStrategy strat = new RandomStrategy(p);
			p.setStrategy(strat);
			return p;
		case 2:
			p = copyPlayer(current);
			strat = new HighestPointsStrategy(p);
			p.setStrategy(strat);
			
			return p;
		case 3:
			p = copyPlayer(current);
			strat = new LowestPointsStrategy(p);
			p.setStrategy(strat);
			
			return p;
		}
		return null;
	}
	public Player copyPlayer(Player other)
	{
		Player p = new Player(other.getName());
		p.setStrategy(other.getStrategy());
		ArrayList<UnoCard> hand = new ArrayList();
		hand.addAll(other.getHand());
		UnoCard card;
		if(other.getLastCardPlayed()!=null)
			card = new UnoCard(other.getLastCardPlayed());
		else
			card=null;
		p.setInfo(other.getName(), hand, card, other.getPoints());
		return p;
	}
}
