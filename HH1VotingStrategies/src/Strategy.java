/**
 * Interface for different concrete strategies
 * @author konao
 *
 */
public interface Strategy {
	/**
	 * Strategy interface
	 * @param candidate
	 * @return getPoints for the candidate according to the concrete strategy class
	 */
	public int getPoints(CandidateData candidate); 
}
