/**
 * Abstract super class for different tabulation strategies
 * @author Kona
 *
 */
public abstract class Voter {
	private TabulationStrategy tabulationStrategy;
	private Candidate[] candidates;
	
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
	public Candidate getWinner()
	{
		return tabulationStrategy.get(candidates);
	}
	public static enum Type
	{
		WINNER,APPROVAL,PLACE
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
}
