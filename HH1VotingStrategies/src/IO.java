import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
/**
 * Class that allows user to use the program
 * @author konao
 *
 */
public class IO {
	/**
	 * The main method for running the I/O
	 * @param args
	 */
	public static void main(String[] args)
	{
		String candidatesFileName="";
		boolean isCorrectFileName=false;
		while(!isCorrectFileName)
		{
			System.out.println("Enter candidates' file name: ");
			Scanner in = new Scanner(System.in);
			candidatesFileName = in.nextLine();
			File candidatesFile = new File(candidatesFileName);
			try {
				BufferedReader br = new BufferedReader(new FileReader(candidatesFile));
				isCorrectFileName=true;
			}
			catch (IOException e) {
				System.out.println(candidatesFileName+" is not a valid file name.");
				isCorrectFileName=false;
			}
		}
		String votesFileName="";
		isCorrectFileName=false;
		while(!isCorrectFileName)
		{
			System.out.println("Enter votes file name: ");
			Scanner in2 = new Scanner(System.in);
			votesFileName = in2.nextLine();
			File votesFile = new File(votesFileName);
			try {
				BufferedReader br = new BufferedReader(new FileReader(votesFile));
				isCorrectFileName=true;
			}
			catch (IOException e) {
				System.out.println(votesFileName+" is not a valid file name.");
				isCorrectFileName=false;
			}
		}
		
		ElectionManagerFactory electionManagerFactory = new ElectionManagerFactory(candidatesFileName,votesFileName);
		boolean isCorrectStrategyID=false;
		String answer="";
		ElectionManager electionManager=null;
		while(!isCorrectStrategyID)
		{
			System.out.println("Enter strategy 1,2,3, or 4");
			Scanner in = new Scanner(System.in);
			try
			{
				answer = in.nextLine();
				int strategyID=Integer.parseInt(answer);
				if(electionManagerFactory.isValidStrategyID(strategyID))
				{
					electionManager = electionManagerFactory.makeElectionManager(strategyID);
					isCorrectStrategyID=true;
				}
				else
				{
					System.out.println(answer+" is an invalid strategy id");
					isCorrectStrategyID=false;
				}
			}
			catch(Exception e)
			{
				System.out.println(answer+" is an invalid strategy id");
				isCorrectStrategyID=false;
			}
			
		}
		
		electionManager.printCandidates();
		System.out.println("The winner is: "+electionManager.getWinner());
		System.out.println("There were "+electionManager.getWinnerPoints()+" points given to "+electionManager.getWinner()+".");
	}
}

