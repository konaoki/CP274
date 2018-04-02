import java.io.*;
import java.util.*;
public class VoterTester {
	public static void main(String[] args)
	{
		VoterFactory voterFactory = new VoterFactory();
		Voter.Type type = Voter.Type.WINNER;
		Voter voter = voterFactory.makeVoter(type);

		File candidatesFile = new File("candidates.txt");
		BufferedReader br=null;
		try {
			br = new BufferedReader(new FileReader(candidatesFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			String st;
			ArrayList<Candidate> candidates=new ArrayList<Candidate>();
			while ((st = br.readLine()) != null)
			{
				candidates.add(new Candidate(st));
			}
			Candidate[] candidatesArray= candidates.toArray(new Candidate[candidates.size()]);
			
			for(int i=0; i<candidatesArray.length; i++)
			{
				System.out.println("candidates: "+candidatesArray[i].toString());
			}
			
			voter.setCandidates(candidatesArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		File voterFile = new File("Voting.csv");
		try {
			br = new BufferedReader(new FileReader(voterFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			String st;
			while ((st = br.readLine()) != null)
			{
				String[] votes=st.split(",");
				for(int i=1; i<votes.length; i++)
				{
					for(int j=0; j<voter.getCandidates().length; j++)
					{
						if(votes[i].equals(voter.getCandidates()[j].getName()))
						{
							voter.getCandidates()[j].add(i-1);
						}
					}
				}
			}
			
			for(int i=0; i<voter.getCandidates().length; i++)
			{
				System.out.println(voter.getCandidates()[i].getName()+" | first: "+voter.getCandidates()[i].getVotes()[0]+" | second: "+voter.getCandidates()[i].getVotes()[1]+" | third: "+voter.getCandidates()[i].getVotes()[2]);
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("result: "+voter.getWinner());
		
	}

	
}

