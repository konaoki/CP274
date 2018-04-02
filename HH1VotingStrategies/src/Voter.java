/**
 * Abstract super class for different tabulation strategies
 * @author Kona
 *
 */
public abstract class Voter {
	private Candidate[] candidates;
	private Type strategyType;
	public static enum Type
	{
		WINNER,APPROVAL,PLACE,LOSER
	}
	protected void setStrategyType(Type strategyType)
	{
		this.strategyType=strategyType;
	}

	public Type getStrategyType()
	{
		return this.strategyType;
	}
	/**
	 * sets the candidates string
	 * @param The candidates as a string array
	 */
	public void setCandidates(Candidate[] candidates)
	{
		this.candidates=candidates;
	}

	public Candidate[] getCandidates()
	{
		return candidates;
	}
	
	/**
	 * Gets the tabulation according to the strategy chosen
	 * @return returns the tabulation as int
	 */
	public Candidate getWinner()
	{		
		int maxIndex=0;
		for(int i=0; i<candidates.length; i++)
		{
			int points = getPoints(candidates[i]);
			if(points>getPoints(candidates[maxIndex]))
			{
				maxIndex=i;
			}
		}
		return candidates[maxIndex];
	}
	
	protected abstract int getPoints(Candidate candidate);
}
