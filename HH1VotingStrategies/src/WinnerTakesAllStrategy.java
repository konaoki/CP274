
public class WinnerTakesAllStrategy implements TabulationStrategy {
	public WinnerTakesAllStrategy()
	{
		System.out.println("winner takes all strategy");
	}
	public Candidate get(Candidate[] candidates)
	{
		int maxIndex=0;
		for(int i=0; i<candidates.length; i++)
		{
			int points = candidates[i].getPoints(Voter.Type.WINNER);
			if(points>candidates[maxIndex].getPoints(Voter.Type.WINNER))
			{
				maxIndex=i;
			}
		}
		return candidates[maxIndex];
	}
}
