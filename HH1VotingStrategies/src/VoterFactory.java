
public class VoterFactory {
	public Voter makeVoter(Voter.Type type)
	{
		if(type==Voter.Type.WINNER)
		{
			return new WinnerTakesAllVoter();
		}
		else if(type==Voter.Type.APPROVAL)
		{
			return new ApprovalRatingVoter();
		}
		else if(type==Voter.Type.PLACE)
		{
			return new PointsPerPlaceVoter();
		}
		return null;
	}
}
