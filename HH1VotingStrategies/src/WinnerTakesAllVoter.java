
public class WinnerTakesAllVoter extends Voter{
	public WinnerTakesAllVoter()
	{
		TabulationStrategy strategy = new WinnerTakesAllStrategy(); 
		setTabulationStrategy(strategy);
	}
}
