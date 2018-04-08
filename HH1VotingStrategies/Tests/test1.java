import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.BeforeClass;
import org.junit.Test;

public class test1 {
	/**
	 * Checks the winner of strategy 1 for files "candidatesTest.txt" and "VotingTest.csv"
	 */
	@Test
	public void strategy1WinnerCheck() {
		ElectionManagerFactory electionManagerFactory = new ElectionManagerFactory("candidatesTest.txt","VotingTest.csv");
		ElectionManager electionManager = electionManagerFactory.makeElectionManager(1);
		CandidateData winner = new CandidateData("candidate1");
		assertTrue(electionManager.getWinner().equals(winner));
	}
	/**
	 * Checks the winner of strategy 2 for files "candidatesTest.txt" and "VotingTest.csv"
	 */
	@Test
	public void strategy2WinnerCheck() {
		ElectionManagerFactory electionManagerFactory = new ElectionManagerFactory("candidatesTest.txt","VotingTest.csv");
		ElectionManager electionManager = electionManagerFactory.makeElectionManager(2);
		CandidateData winner = new CandidateData("candidate2");
		assertTrue(electionManager.getWinner().equals(winner));
	}
	/**
	 * Checks the winner of strategy 3 for files "candidatesTest.txt" and "VotingTest.csv"
	 */
	@Test
	public void strategy3WinnerCheck() {
		ElectionManagerFactory electionManagerFactory = new ElectionManagerFactory("candidatesTest.txt","VotingTest.csv");
		ElectionManager electionManager = electionManagerFactory.makeElectionManager(3);
		CandidateData winner = new CandidateData("candidate3");
		assertTrue(electionManager.getWinner().equals(winner));
	}
	/**
	 * Checks the winner of strategy 4 for files "candidatesTest.txt" and "VotingTest.csv"
	 */
	@Test
	public void strategy4WinnerCheck() {
		ElectionManagerFactory electionManagerFactory = new ElectionManagerFactory("candidatesTest.txt","VotingTest.csv");
		ElectionManager electionManager = electionManagerFactory.makeElectionManager(4);
		CandidateData winner = new CandidateData("candidate4");
		assertTrue(electionManager.getWinner().equals(winner));
	}
	/**
	 * Checks the points for the winner of strategy 1 for files "candidatesTest.txt" and "VotingTest.csv"
	 */
	@Test
	public void strategy1PointsCheck() {
		ElectionManagerFactory electionManagerFactory = new ElectionManagerFactory("candidatesTest.txt","VotingTest.csv");
		ElectionManager electionManager = electionManagerFactory.makeElectionManager(1);
		int points =electionManager.getWinnerPoints();
		assertTrue(points==3);
	}
	/**
	 * Checks the points for the winner of strategy 2 for files "candidatesTest.txt" and "VotingTest.csv"
	 */
	@Test
	public void strategy2PointsCheck() {
		ElectionManagerFactory electionManagerFactory = new ElectionManagerFactory("candidatesTest.txt","VotingTest.csv");
		ElectionManager electionManager = electionManagerFactory.makeElectionManager(2);
		CandidateData winner = new CandidateData("candidate2");
		assertTrue(electionManager.getWinner().equals(winner));
		int points =electionManager.getWinnerPoints();
		assertTrue(points==5);
	}
	/**
	 * Checks the points for the winner of strategy 3 for files "candidatesTest.txt" and "VotingTest.csv"
	 */
	@Test
	public void strategy3PointsCheck() {
		ElectionManagerFactory electionManagerFactory = new ElectionManagerFactory("candidatesTest.txt","VotingTest.csv");
		ElectionManager electionManager = electionManagerFactory.makeElectionManager(3);
		CandidateData winner = new CandidateData("candidate3");
		assertTrue(electionManager.getWinner().equals(winner));
		int points =electionManager.getWinnerPoints();
		assertTrue(points==15);
	}
	/**
	 * Checks the points for the winner of strategy 4 for files "candidatesTest.txt" and "VotingTest.csv"
	 */
	@Test
	public void strategy4PointsCheck() {
		ElectionManagerFactory electionManagerFactory = new ElectionManagerFactory("candidatesTest.txt","VotingTest.csv");
		ElectionManager electionManager = electionManagerFactory.makeElectionManager(4);
		CandidateData winner = new CandidateData("candidate4");
		assertTrue(electionManager.getWinner().equals(winner));
		int points =electionManager.getWinnerPoints();
		assertTrue(points==-6);
	}
	/**
	 * Checks for exception thrown by election manager factory with incorrect strategy id
	 */
	@Test(expected = IllegalArgumentException.class) 
	public void ExceptionCheck() {
		ElectionManagerFactory electionManagerFactory = new ElectionManagerFactory("candidatesTest.txt","VotingTest.csv");
		ElectionManager electionManager = electionManagerFactory.makeElectionManager(5);
	}

}
