
public class LoserTakesAllVoter extends Voter{
	public LoserTakesAllVoter()
	{
		setStrategyType(Voter.Type.LOSER);
	}
	protected int getPoints(Candidate candidate)
	{
		int points=0;
		int[] votes = candidate.getVotes();
		points=votes[0]*-3+votes[1]*-2+votes[2]*-1;
		return points;
	}
}
