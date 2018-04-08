/**
 * The class for creating an ElectionManager.
 * @author konao
 *
 */
public class ElectionManagerFactory {
	String candidatesFileName;
	String votesFileName;
	
	/**
	 * Creates a new electionManagerFactory
	 * @param candidatesFileName
	 * @param votesFileName
	 */
	public ElectionManagerFactory(String candidatesFileName, String votesFileName)
	{
		this.candidatesFileName=candidatesFileName;
		this.votesFileName=votesFileName;
	}
	/**
	 * Instantiates ElectionManager according to the strategy ID given by user
	 * @param strategyID
	 * @return
	 */
	public ElectionManager makeElectionManager(int strategyID)
	{
		if(strategyID==1)
		{
			return new ElectionManager(new WinnerTakesAllStrategy(),candidatesFileName, votesFileName);
		}
		else if(strategyID==2)
		{
			return new ElectionManager(new ApprovalRatingStrategy(),candidatesFileName, votesFileName);
		}
		else if(strategyID==3)
		{
			return new ElectionManager(new PointsPerPlaceStrategy(),candidatesFileName, votesFileName);
		}
		else if(strategyID==4)
		{
			return new ElectionManager(new LoserTakesAllStrategy(),candidatesFileName, votesFileName);
		}
		else
		{
			throw new IllegalArgumentException("Invalid strategy selection.");
		}
	}
	/**
	 * Checks if the id given is a valid strategy id
	 * @param strategyID
	 * @return returns true if the strategyID is valid, vice versa
	 */
	public boolean isValidStrategyID(int strategyID)
	{
		if(strategyID==1 ||strategyID==2||strategyID==3||strategyID==4)
		{
			return true;
		}
		return false;
	}
}