/**
 * Calculates points by multiplying first place vote by 3, second by 2, and third by 1.
 * @author konao
 *
 */
public class PointsPerPlaceStrategy implements Strategy{
	/**
	 * getPoints method for PointsPerPlaceStrategy 
	 * @return the points calculated
	 */
	public int getPoints(CandidateData candidate)
	{
		int points=0;
		int[] votes = candidate.getVotes();
		points=votes[0]*3+votes[1]*2+votes[2];
		return points;
	}
}
