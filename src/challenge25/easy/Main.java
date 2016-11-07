package challenge25.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

	final static int MAX_VOTERS = 500000;

	private static class Candidate {
		private String id;
		private int votes;

		Candidate(String id) {
			this.setId(id);
			this.setVotes(0);
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public int getVotes() {
			return votes;
		}

		public void setVotes(int votes) {
			this.votes = votes;
		}

		public void increaseVote() {
			this.votes++;
		}

		public void dump() {
			System.out.println("Candidate ID: " + this.id + "\nVotes: " + this.votes + " "
					+ (this.votes * 100.0f) / MAX_VOTERS + "%");

		}

	}

	public static void main(String[] args) {
		List<Candidate> candidateList = new ArrayList<>();
		candidateList.add(new Candidate("A"));
		candidateList.add(new Candidate("B"));

		Random rdm = new Random();
		for (int i = 0; i < MAX_VOTERS; i++)
			candidateList.get(rdm.nextInt(candidateList.size())).increaseVote();

		Candidate winner = null;
		for (Candidate candidate : candidateList) {
			candidate.dump();
			System.out.println();
			if (candidate.getVotes() > (MAX_VOTERS / 2))
				winner = candidate;
		}

		if (winner != null)
			System.out.println("\nWinner: Candidate " + winner.getId());
		else
			System.out.println("\nNo One Wins");

	}

}
