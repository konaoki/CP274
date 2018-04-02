
public class WinnerTakesAllVoter extends Voter{
	public WinnerTakesAllVoter()
	{
		setStrategyType(Voter.Type.WINNER);
	}
	protected int getPoints(Candidate candidate)
	{
		int points=0;
		int[] votes = candidate.getVotes();
		points=votes[0];
		return points;
	}
}
