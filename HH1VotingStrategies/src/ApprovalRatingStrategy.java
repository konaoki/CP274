/**
 * Calculates points by giving a point for first and second place votes.
 * @author konao
 *
 */
public class ApprovalRatingStrategy implements Strategy{
	/**
	 * getPoints method for ApprovalRatingStrategy 
	 * @return the points calculated
	 */
	public int getPoints(CandidateData candidate)
	{
		int points=0;
		int[] votes = candidate.getVotes();
		points=votes[0]+votes[1];
		return points;
	}
}
