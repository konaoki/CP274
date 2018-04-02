
public class Candidate{
	private String name;
	private int[] votes;
	
	public Candidate(String name)
	{
		this.name = name;
		votes= new int[3];
	}
	public String getName()
	{
		return this.name;
	}
	public String toString()
	{
		return this.name;
	}
	public void addVote(int i)
	{
		votes[i]++;
	}
	public int[] getVotes()
	{
		return votes;
	}
	
	public boolean equals(Candidate other)
	{
		if(other.getName().equals(this.getName()))
		{
			return true;
		}
		return false;
	}
}
