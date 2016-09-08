package organizer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * Reads through all content all challenge directories
 * Extracts content from files
 * Format content and update README.md
 * 
 */

public class ReadMe {
	private static final String README_MD = "./README.md";
	private static final String CHALLENGES_PATH = "./src";
	private static final String CHALLENGE_TXT = "Challenge.txt";
	private static final String MAIN_JAVA = "Main.java";

	private static class Challenge extends File {
		private static final long serialVersionUID = 1L;
		private int challengeNum;
		private ArrayList<Difficulty> difficulties;

		public Challenge(String pathname) {
			super(pathname);
			try {
				this.challengeNum = Integer.parseInt(this.getName().replaceAll("challenge", ""));
				parseChallenges();
				Collections.sort(this.difficulties, new DifficultyComparator());
			} catch (Exception e) {
				System.out.println("Unable To Parse Number From: " + this.getName());
			}
		}

		public int getChallengeNum() {
			return this.challengeNum;
		}

		public boolean isEmpty() {
			for (int i = 0; i < this.difficulties.size(); i++)
				if (this.difficulties.get(i).getDesc() == null || this.difficulties.get(i).getDesc().isEmpty())
					return true;
			return false;
		}

		public String dump() {
			StringBuilder sb = new StringBuilder();
			String challengeStr = ("#### Challenge #" + this.challengeNum);
			System.out.println(challengeStr + "\n");
			sb.append(challengeStr + "\n\n");
			for (int i = 0; i < this.difficulties.size(); i++) {
				sb.append(this.difficulties.get(i).dump());
				System.out.println();
				sb.append("\n");
			}
			return sb.toString();
		}

		private void parseChallenges() {
			this.difficulties = new ArrayList<>();
			for (File difficulty : this.listFiles()) {
				if (difficulty.isDirectory()) {
					this.difficulties.add(new Difficulty(difficulty.getAbsolutePath()));
				}
			}
		}

		private class DifficultyComparator implements Comparator<Difficulty> {
			@Override
			public int compare(Difficulty o1, Difficulty o2) {
				return Integer.compare(o1.getDifficulty(), o2.getDifficulty());
			}
		}

		private class Difficulty extends File {
			private static final long serialVersionUID = 1L;
			private String url;
			private String desc;
			private boolean incomplete = false;

			public Difficulty(String pathname) {
				super(pathname);
				parseChallengeInfo();
				incompleteCheck();
			}

			public String getDesc() {
				return this.desc;
			}

			public int getDifficulty() {
				switch (this.getName()) {
				case "easy":
					return 0;
				case "intermediate":
					return 1;
				case "hard":
					return 2;
				default:
					return 99;
				}
			}

			public String dump() {
				StringBuilder sb = new StringBuilder();
				String header = "* [" + this.getName() + "](" + this.url + ")" + (incomplete ? " (incomplete)" : "");
				String description = "> " + this.desc + "\n";
				System.out.println(header);
				System.out.println(description);
				sb.append(header + "\n");
				sb.append(description);
				return sb.toString();
			}

			private void parseChallengeInfo() {
				try {
					BufferedReader br = new BufferedReader(new FileReader(this + File.separator + CHALLENGE_TXT));
					String line;
					this.desc = "";
					while ((line = br.readLine()) != null) {
						if (line.isEmpty())
							continue;

						if (line.startsWith("https://www.reddit.com/r/dailyprogrammer/")) {
							this.url = line;
						} else {
							this.desc += line + " ";
						}
					}
					this.desc = this.desc.trim();
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			private void incompleteCheck() {
				try {
					BufferedReader br = new BufferedReader(new FileReader(this + File.separator + MAIN_JAVA));
					String line;
					while ((line = br.readLine()) != null) {
						if (!this.incomplete && line.contains("INCOMPLETE")) {
							this.incomplete = true;
							break;
						}
					}
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

	}

	private static class ChallengeComparator implements Comparator<Challenge> {
		@Override
		public int compare(Challenge o1, Challenge o2) {
			return Integer.compare(o1.getChallengeNum(), o2.getChallengeNum());
		}
	}

	private static void createMD(ArrayList<Challenge> challenges) {
		final String title = "# DailyProgrammer\n[DailyProgrammer Subreddit](https://www.reddit.com/r/dailyprogrammer/)\n | [List of Challenges](https://www.reddit.com/r/dailyprogrammer/wiki/challenges)\n";

		try {
			PrintWriter pw = new PrintWriter(README_MD);

			System.out.println(title);
			pw.println(title);
			for (int i = 0; i < challenges.size(); i++) {
				pw.print(challenges.get(i).dump());
				if (i != challenges.size() - 1)
					pw.println("---\n");
			}
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ArrayList<Challenge> challenges = new ArrayList<>();

		for (File challenge : new File(CHALLENGES_PATH).listFiles()) {
			if (challenge.getName().contains("challenge"))
				challenges.add(new Challenge(challenge.getAbsolutePath()));
		}

		for (int i = challenges.size() - 1; i >= 0; i--) {
			if (challenges.get(i).isEmpty())
				challenges.remove(i);
		}

		Collections.sort(challenges, new ChallengeComparator());

		createMD(challenges);
	}

}
