
public class Candidate{
	private String name;
	private int[] votes;
	private int points;
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
	public void add(int i)
	{
		votes[i]++;
	}
	public int[] getVotes()
	{
		return votes;
	}
	private void setPoints(Voter.Type type)
	{
		//setpoints according to the type
		if(type==Voter.Type.WINNER)
		{
			points=votes[0];
		}
		else if(type==Voter.Type.APPROVAL)
		{
			points=votes[0]+votes[1];
		}
		else if(type==Voter.Type.PLACE)
		{
			points=votes[0]*3+votes[1]*2+votes[2];
		}
	}
	public int getPoints(Voter.Type type)
	{
		setPoints(type);
		return this.points;
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
