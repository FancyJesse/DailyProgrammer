package organizer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

/*
 * This generator will take in a daily programmer challenge 
 * URL and create directory including a blank Main.java file 
 * and a challenge.txt file
 *  
 */

public class ChallengeGenerator {

	private static final String USER_AGENT = "Java Program:github.com/FancyJesse/DailyProgrammer:v1.0 (by /u/FancyJesse)";
	private static final String CHALLENGES_PATH = "./src";
	private static final String CHALLENGE_TXT = "challenge.txt";
	private static final String MAIN_JAVA = "Main.java";

	private static class Challenge {
		private String url;
		private String title;
		private String difficulty;
		private String description;

		public boolean readChallengeURL() {

			try {

				URLConnection connection = (new URL(this.url)).openConnection();
				Thread.sleep(2000); // required for rate limiter
				connection.setRequestProperty("User-Agent", USER_AGENT);
				InputStream is = connection.getInputStream();

				BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				System.out.println();
				String headerLine = br.readLine();

				if (!headerLine.contains("https://www.reddit.com/r/dailyprogrammer/")) {
					System.out.println("URL Is Not DailyProgrammer Subreddit: " + this.url);
					return false;
				}

				String titleTag = headerLine.substring(headerLine.indexOf("<title>") + "<title>".length(),
						headerLine.indexOf("</title>") + "</title>".length());

				String challengeNumber = titleTag.substring(titleTag.indexOf("#"));
				setTitle("challenge" + challengeNumber.substring(1, challengeNumber.indexOf(" ")));

				try {
					setDifficulty(
							challengeNumber.substring(challengeNumber.indexOf("[") + 1, challengeNumber.indexOf("]")));
				} catch (Exception e) {
					System.out.println("Unable To Extract Difficulty\nPlease Insert Manually");
					setDifficulty(getInput("Difficulty:"));
				}

				String descriptionTag = headerLine.substring(headerLine.indexOf("<meta name=\"description\" content=\"")
						+ "<meta name=\"description\" content=\"".length());
				setDescription(descriptionTag.substring(0, descriptionTag.indexOf("\" />")));

				is.close();
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

			return true;
		}

		public String getDifficultyPath() {
			return title + "/" + difficulty;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title.trim();
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url.trim();
		}

		public String getDifficulty() {
			return difficulty;
		}

		public void setDifficulty(String difficulty) {
			difficulty = difficulty.toLowerCase().trim();

			switch (difficulty) {
			case "easy":
			case "intermediate":
			case "hard":
				this.difficulty = difficulty;
				break;
			case "difficult":
				this.difficulty = "hard";
				break;
			default:
				System.out.println("Unable To Identify Difficulty: " + difficulty);
				System.out.println("Please Insert Manually");
				this.difficulty = getInput("Difficulty:");
			}
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description.trim();

			// only short description is extracted
			if (this.description.endsWith("...")) {
				this.description += " [See Challenge Thread For Full Description]";
			}

		}

		public void dump() {
			System.out.println(url + "\n" + title + " [" + difficulty + "]\n" + description);
		}

	}

	private static void createChallengeDirectory(Challenge challenge) {

		File rootDir = new File(CHALLENGES_PATH);
		if (!rootDir.exists()) {
			System.out.println("Unable to find root path: " + rootDir.getAbsolutePath());
			return;
		}

		File challengeDifficultyDir = new File(rootDir + File.separator + challenge.getDifficultyPath());
		challengeDifficultyDir.mkdirs();
		if (challengeDifficultyDir.listFiles().length != 0) {
			System.out
					.println("Challenge Directory Already Contains Files: " + challengeDifficultyDir.getAbsolutePath());
			System.out.println("\t" + challengeDifficultyDir.list());
			return;
		}

		File javaFile = new File(challengeDifficultyDir + File.separator + MAIN_JAVA);
		try {
			PrintWriter pr = new PrintWriter(javaFile);
			pr.println("package " + challenge.getTitle() + "." + challenge.getDifficulty() + ";\n");
			pr.println("/*\n * INCOMPLETE\n *\n */\n");
			pr.println("public class Main {\n\n\tpublic static void main(String[] args) {\n\t}\n\n}");
			pr.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error Writing to: " + javaFile.getAbsolutePath());
			return;
		}

		File descFile = new File(challengeDifficultyDir + File.separator + CHALLENGE_TXT);
		try {
			PrintWriter pr = new PrintWriter(descFile);
			pr.println(challenge.getUrl());
			pr.print("\n" + challenge.getDescription());
			pr.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error Writing to: " + javaFile.getAbsolutePath());
			return;
		}

		System.out.println("Successfully Created Challenge:");
		challenge.dump();
	}

	private static String getInput(String prompt) {
		while (true) {
			try {
				System.out.print(prompt + "\n> ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				return br.readLine();
			} catch (Exception e) {
				System.out.println("Something Went Wrong. Try Again.");
			}
		}
	}

	public static void main(String[] args) {

		Challenge challenge = new Challenge();

		String challengeUrl = getInput("Challenge URL:");
		challenge.setUrl(challengeUrl);

		boolean successReading;
		successReading = challenge.readChallengeURL();

		if (successReading)
			createChallengeDirectory(challenge);

	}

}
