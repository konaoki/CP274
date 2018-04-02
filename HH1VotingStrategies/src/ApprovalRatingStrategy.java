
public class ApprovalRatingStrategy implements TabulationStrategy {
	public ApprovalRatingStrategy()
	{
		System.out.println("approval rating strategy");
	}
	public Candidate get(Candidate[] candidates)
	{
		int maxIndex=0;
		for(int i=0; i<candidates.length; i++)
		{
			int points = candidates[i].getPoints(Voter.Type.APPROVAL);
			if(points>candidates[maxIndex].getPoints(Voter.Type.APPROVAL))
			{
				maxIndex=i;
			}
		}
		return candidates[maxIndex];
	}
}
