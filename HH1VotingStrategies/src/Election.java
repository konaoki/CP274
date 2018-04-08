/**
 * Class for storing data of candidates (which holds the data for votes) and the strategy chosen by its manager.
 * @author Kona
 *
 */
public class Election {
	private CandidateData[] candidates;
	private Strategy strategy;
	/**
	 * gets strategy type
	 * @return returns strategy type as enum
	 */
	public Strategy getStrategy()
	{
		return this.strategy;
	}
	/**
	 * sets the strategy this election will obey
	 * @param strategy the strategy the Election algorithm has been told by the manager
	 */
	public void setStrategy(Strategy strategy)
	{
		this.strategy = strategy;
	}
	/**
	 * sets the candidates string
	 * @param The candidates as a string array
	 */
	public void setCandidates(CandidateData[] candidates)
	{
		this.candidates=candidates;
	}
	/**
	 * Gets all candidate data the Election holds as array
	 * @return candidates data as array
	 */
	public CandidateData[] getCandidates()
	{
		return candidates;
	}
	
	/**
	 * Gets the tabulation according to the strategy chosen
	 * @return returns the tabulation as int
	 */
	public CandidateData calculateWinner()
	{		
		int maxIndex=0;
		for(int i=0; i<candidates.length; i++)
		{
			int points = strategy.getPoints(candidates[i]);
			if(points>strategy.getPoints(candidates[maxIndex]))
			{
				maxIndex=i;
			}
		}
		return candidates[maxIndex];
	}
	
}
