/**
 * Loser takes all strategy. First place vote gets multiplied by -3, second by -2, and the third by -1. Whoever gets the highest points (will be negative) wins.
 * @author konao
 *
 */
public class LoserTakesAllStrategy implements Strategy{
	/**
	 * getPoints() method for LoserTakesAllStrategy
	 */
	public int getPoints(CandidateData candidate)
	{
		int points=0;
		int[] votes = candidate.getVotes();
		points=votes[0]*-3+votes[1]*-2+votes[2]*-1;
		return points;
	}
}
