
public class PointsPerPlaceVoter extends Voter{
	public PointsPerPlaceVoter()
	{
		TabulationStrategy strategy = new PointsPerPlaceStrategy(); 
		setTabulationStrategy(strategy);
	}
}
