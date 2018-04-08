/**
 * Class that holds each candidate information
 * @author konao
 *
 */
public class CandidateData{
	private String name;
	private int[] votes;
	/**
	 * constructor for constructor
	 * @param name takes in the name of the candidate
	 */
	public CandidateData(String name)
	{
		this.name = name;
		votes= new int[3];
	}
	/**
	 * gets the name of the candidate
	 * @return the name of the candidate
	 */
	public String getName()
	{
		return this.name;
	}
	/**
	 * same as getName()
	 * @return name
	 */
	public String toString()
	{
		return this.name;
	}
	/**
	 * Adds vote to the specified index
	 * @param i the index for vote, 0 corresponding to first place, 1 to second, and 2 to third
	 */
	public void addVote(int i)
	{
		votes[i]++;
	}
	/**
	 * gets the votes as int[]
	 * @return returns the votes
	 */
	public int[] getVotes()
	{
		return votes;
	}
	/**
	 * checks if two candidates are equals
	 * @param other
	 * @return returns true if the two candidates' names are the same
	 */
	public boolean equals(CandidateData other)
	{
		if(other.getName().equals(this.getName()))
		{
			return true;
		}
		return false;
	}
}
