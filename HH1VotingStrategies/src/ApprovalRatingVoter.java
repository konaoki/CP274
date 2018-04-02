
public class ApprovalRatingVoter extends Voter{
	public ApprovalRatingVoter()
	{
		TabulationStrategy strategy = new ApprovalRatingStrategy(); 
		setTabulationStrategy(strategy);
	}
}
