
public class WinnerTakesAllStrategy implements TabulationStrategy {
	public WinnerTakesAllStrategy()
	{
		System.out.println("winner takes all strategy");
	}
	public String get(String[] candidates, String[][] votes)
	{
		int[] countPerIndex=new int[candidates.length];
		for(int i=0; i<votes.length; i++)
		{
			for(int j=0; j<candidates.length; j++)
			{
				if(votes[i][1]==candidates[j])
				{
					countPerIndex[j]++;
					break;
				}
			}
		}
		int maxIndex=0;
		for(int i=0; i<countPerIndex.length; i++)
		{
			if(countPerIndex[i]>countPerIndex[maxIndex])
			{
				maxIndex=i;
			}
		}
		return candidates[maxIndex];
	}
}
