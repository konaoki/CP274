
public class Candidate {
	private String name;
	private int points;
	public Candidate(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}
	public int getPoints()
	{
		return this.points;
	}
	public void addPoint(int points)
	{
		this.points+=points;
	}
	public void setPoints(int points)
	{
		this.points=points;
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
