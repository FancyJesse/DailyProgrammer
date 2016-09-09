package challenge19.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class Main {
	private static final String bookUrl = "http://www.gutenberg.org/cache/epub/1661/pg1661.txt";
	private static int charCount;
	private static List<String> chapters = Arrays.asList("I.", "A Scandal in Bohemia", "II.", "The Red-headed League",
			"III.", "A Case of Identity", "IV.", "The Boscombe Valley Mystery", "V.", "The Five Orange Pips", "VI.",
			"The Man with the Twisted Lip", "VII.", "The Adventure of the Blue Carbuncle", "VIII.",
			"The Adventure of the Speckled Band", "IX.", "The Adventure of the Engineer's Thumb", "X.",
			"The Adventure of the Noble Bachelor", "XI.", "The Adventure of the Beryl Coronet", "XII.",
			"The Adventure of the Copper Beeches");

	private static void countChars() {
		try {
			URL url = new URL(bookUrl);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

			String line;
			boolean start = false;
			while ((line = br.readLine()) != null) {
				if (line.contains("End of the Project Gutenberg"))
					break;

				if (!start && line.startsWith("ADVENTURE"))
					start = true;

				if (start && !(line.contains("ADVENTURE") || chapters.contains(line)))
					charCount += line.replaceAll("[^A-Za-z0-9]", "").trim().length();
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < chapters.size(); i++)
			chapters.set(i, chapters.get(i).toUpperCase());
		charCount = 0;
		countChars();
		System.out.println("Character Count: " + charCount);
	}

}
