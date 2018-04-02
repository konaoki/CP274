
public class ApprovalRatingVoter extends Voter{
	public ApprovalRatingVoter()
	{
		setStrategyType(Voter.Type.APPROVAL);
	}
	protected int getPoints(Candidate candidate)
	{
		int points=0;
		int[] votes = candidate.getVotes();
		points=votes[0]+votes[1];
		return points;
	}
}
