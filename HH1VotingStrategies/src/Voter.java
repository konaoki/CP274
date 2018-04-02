/**
 * Abstract super class for different tabulation strategies
 * @author Kona
 *
 */
public abstract class Voter {
	private TabulationStrategy tabulationStrategy;
	private String[] candidates;
	private String[][] votes; 
	
	/**
	 * Sets the counting strategy for this TabulationMethod
	 * @param tabulationStrategy
	 */
	public void setTabulationStrategy(TabulationStrategy tabulationStrategy)
	{
		this.tabulationStrategy=tabulationStrategy;
	}

	/**
	 * Gets the tabulation according to the strategy chosen
	 * @return returns the tabulation as int
	 */
	public String getWinner()
	{
		return tabulationStrategy.get(candidates, votes);
	}
	public static enum Type
	{
		WINNER,APPROVAL,PLACE
	}
	
	/**
	 * sets the candidates string
	 * @param The candidates as a string array
	 */
	public void setCandidates(String[] candidates)
	{
		this.candidates=candidates;
	}
	/**
	 * Sets the vote data as 2d array
	 * @param The vote data as 2d array
	 */
	public void setVotes(String[][] votes)
	{
		this.votes=votes;
	}
}
