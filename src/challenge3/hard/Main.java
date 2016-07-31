package challenge3.hard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {

	private final static String FILE_PATH = "./src/challenge3/hard/jSD873gL.txt";
	private final static String[] SCRAMBLED_WORDS = { "mkeart", "sleewa", "edcudls", "iragoge", "usrlsle", "nalraoci",
			"nsdeuto", "amrhat", "inknsy", "iferkna" };
	private static ArrayList<String> allLines;

	private static class DeScrambler {
		private String scrammbledWord;
		private ArrayList<String> matchedWordList;

		public DeScrambler(String scrammbledWord) {
			this.scrammbledWord = scrammbledWord;
			this.matchedWordList = new ArrayList<>();
		}

		public void findMatches() {

			for (int i = 0; i < allLines.size(); i++) {
				if (allLines.get(i).length() != scrammbledWord.length())
					continue;

				int index;
				char[] scrammbledWordCharArray = scrammbledWord.toCharArray();
				char[] lineCharArray = allLines.get(i).toCharArray();
				for (int j = 0; j < lineCharArray.length; j++) {
					index = getIndexOf(scrammbledWordCharArray, lineCharArray[j]);
					if (index == -1)
						break;

					scrammbledWordCharArray[index] = '\u0000';

					if (j == scrammbledWord.length() - 1) {
						matchedWordList.add(allLines.get(i));
					}
				}
			}
		}

		private void dump() {
			System.out.println("Scrammbled Word: " + this.scrammbledWord);
			System.out.println("Matches[" + matchedWordList.size() + "]: ");
			for (int i = 0; i < matchedWordList.size(); i++) {
				System.out.println("\t" + matchedWordList.get(i));
			}
		}

	}

	private static int getIndexOf(char[] charArray, char ch) {
		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] == ch) {
				return i;
			}
		}
		return -1;
	}

	private static void readAllLines(File file) {
		allLines = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				allLines.add(line.trim());
			}
			br.close();
		} catch (IOException e) {
		}
	}

	private static void dumpSortedList() {
		Collections.sort(allLines, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return s1.length() - s2.length();
			}
		});

		System.out.println("Dumping Sorted List: ");
		for (String line : allLines) {
			System.out.println(line);
		}
	}

	public static void main(String[] args) {
		File file = new File(FILE_PATH);
		if (file.exists()) {
			readAllLines(file);
			for (int i = 0; i < SCRAMBLED_WORDS.length; i++) {
				DeScrambler deScrambler = new DeScrambler(SCRAMBLED_WORDS[i]);
				deScrambler.findMatches();
				deScrambler.dump();
				System.out.println();
			}
			dumpSortedList();
		} else {
			System.out.println("File Does Not Exist: " + file.getPath());
		}
	}

}
