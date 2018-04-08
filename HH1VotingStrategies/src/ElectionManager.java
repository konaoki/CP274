import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Class for handling file reading and parsing. Manages an Election class and passes values back to the user.
 * @author konao
 *
 */
public class ElectionManager {
	private Election election;
	Strategy strategy;
	String candidatesFileName;
	String votesFileName;
	/**
	 * constructor for Election Manager
	 * @param strategy the strategy the manager is told to use for the Election
	 */
	public ElectionManager(Strategy strategy, String candidatesFileName, String votesFileName)
	{
		this.strategy=strategy;
		this.candidatesFileName=candidatesFileName;
		this.votesFileName=votesFileName;
	}
	/**
	 * get the amount of points the winner got
	 * @return the amount of points
	 */
	public int getWinnerPoints()
	{
		return strategy.getPoints(getWinner());
	}
	/**
	 * prints the candidates
	 */
	public void printCandidates()
	{
		getWinner();
		System.out.println("There were "+election.getCandidates().length+" candidates.");
		for(int i=0; i<election.getCandidates().length; i++)
		{
			System.out.println(election.getCandidates()[i].getName());
		}
	}
	/**
	 * retrieves the winner according to the strategy chosen from the Election
	 * @return the candidate chosen as winner
	 */
	public CandidateData getWinner()
	{
		election = new Election();
		election.setStrategy(strategy);
		File candidatesFile = new File(candidatesFileName);
		File votesFile = new File(votesFileName);

		ArrayList<CandidateData> candidates=new ArrayList<CandidateData>();
		BufferedReader br=null;

		try {
			br = new BufferedReader(new FileReader(candidatesFile));
			String candidateString;
			while ((candidateString = br.readLine()) != null){
				candidates.add(new CandidateData(candidateString));
			}

			br = new BufferedReader(new FileReader(votesFile));
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

			CandidateData[] candidatesArray= candidates.toArray(new CandidateData[candidates.size()]);
			election.setCandidates(candidatesArray);


			

		} catch (IOException e) {
			e.printStackTrace();
		}


		return election.calculateWinner();
	}
}
