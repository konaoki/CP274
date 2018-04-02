import java.io.*;
import java.util.*;
public class VoterTester {
	public static void main(String[] args)
	{
		VoterFactory voterFactory = new VoterFactory();
		Voter.Type type = Voter.Type.WINNER;
		Voter voter = voterFactory.makeVoter(type);

		File candidatesFile = new File("candidates.txt");
		File voterFile = new File("Voting.csv");

		ArrayList<Candidate> candidates=new ArrayList<Candidate>();
		BufferedReader br=null;
		
		try {
			br = new BufferedReader(new FileReader(candidatesFile));
			String candidateString;
			while ((candidateString = br.readLine()) != null){
				candidates.add(new Candidate(candidateString));
			}

			br = new BufferedReader(new FileReader(voterFile));
			String voteString;
			while ((voteString = br.readLine()) != null){
				String[] votes=voteString.split(",");
				for(int i=1; i<votes.length; i++){
					for(int j=0; j<candidates.size(); j++){
						if(votes[i].equals(candidates.get(j).getName())){
							candidates.get(j).addVote(i-1);
						}
					}
				}
			}
			
			Candidate[] candidatesArray= candidates.toArray(new Candidate[candidates.size()]);
			voter.setCandidates(candidatesArray);
			
	
			for(int i=0; i<candidates.size(); i++)
			{
				System.out.println(candidates.get(i).getName()+" | first: "+candidates.get(i).getVotes()[0]+" | second: "+candidates.get(i).getVotes()[1]+" | third: "+candidates.get(i).getVotes()[2]);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}


		System.out.println("result for strategy type "+voter.getStrategyType()+": "+voter.getWinner());

	}


}

