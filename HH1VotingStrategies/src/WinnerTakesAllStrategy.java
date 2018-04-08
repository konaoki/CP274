/**
 * Winner takes all strategy. Points equals the number of first place votes the candidate has.
 * @author konao
 *
 */
public class WinnerTakesAllStrategy implements Strategy{
	/**
	 * getPoints method for WinnerTakesAllStrategy 
	 * @return the points calculated
	 */
	public int getPoints(CandidateData candidate)
	{
		int points=0;
		int[] votes = candidate.getVotes();
		points=votes[0];
		return points;
	}
}
