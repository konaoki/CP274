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
			ArrayList<String> candidates=new ArrayList<String>();
			while ((st = br.readLine()) != null)
			{
				candidates.add(st);
			}
			String[] candidatesArray= candidates.toArray(new String[candidates.size()]);
			/*
			for(int i=0; i<candidatesArray.length; i++)
			{
				System.out.println("candidates: "+candidatesArray[i]);
			}
			*/
			voter.setCandidates(candidatesArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
		File voterFile = new File("VotingShort1.csv");
		try {
			br = new BufferedReader(new FileReader(voterFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			String st;
			ArrayList<String[]> votes = new ArrayList<String[]>();
			String[] vote = new String[4];
			while ((st = br.readLine()) != null)
			{
				vote=st.split(",");
				votes.add(vote);
			}
			String[][] votesArray = votes.toArray(new String[votes.size()][4]);
			/*
			for(int i=0; i<votesArray.length; i++)
			{
				System.out.println("voter: "+votesArray[i][0]+" 1: "+votesArray[i][1]+" 2: "+votesArray[i][2]+" 3: "+votesArray[i][3]);
			}
			*/
			voter.setVotes(votesArray);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(voter.getWinner());
		
	}

	
}

