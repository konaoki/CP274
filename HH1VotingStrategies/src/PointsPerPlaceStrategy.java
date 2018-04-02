
public class PointsPerPlaceStrategy implements TabulationStrategy{
	public PointsPerPlaceStrategy()
	{
		System.out.println("points per place strategy");
	}
	public Candidate get(Candidate[] candidates)
	{
		int maxIndex=0;
		for(int i=0; i<candidates.length; i++)
		{
			int points = candidates[i].getPoints(Voter.Type.PLACE);
			if(points>candidates[maxIndex].getPoints(Voter.Type.PLACE))
			{
				maxIndex=i;
			}
		}
		return candidates[maxIndex];
	}
}
