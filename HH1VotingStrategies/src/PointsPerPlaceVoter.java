
public class PointsPerPlaceVoter extends Voter{
	public PointsPerPlaceVoter()
	{
		setStrategyType(Voter.Type.PLACE);
	}
	protected int getPoints(Candidate candidate)
	{
		int points=0;
		int[] votes = candidate.getVotes();
		points=votes[0]*3+votes[1]*2+votes[2];
		return points;
	}
}
