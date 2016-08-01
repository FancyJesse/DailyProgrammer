package challenge5.intermediate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Closely related to challenge3/hard
 * 
 */

public class Main {
	private final static String FILE_PATH = "./src/challenge5/intermediate/alice30.txt";
	private static ArrayList<String> allWords;

	private static class Anagrams {
		private String word;
		private ArrayList<String> anagramList;

		public Anagrams(String word) {
			this.word = word;
			this.anagramList = new ArrayList<>();
		}

		public void findMatches() {

			for (int i = 0; i < allWords.size(); i++) {
				if ((allWords.get(i).length() != word.length()) || allWords.get(i).equals(word))
					continue;

				int index;
				char[] wordCharArray = word.toCharArray();
				char[] allWordItemCharArray = allWords.get(i).toCharArray();
				for (int j = 0; j < allWordItemCharArray.length; j++) {
					index = getIndexOf(wordCharArray, allWordItemCharArray[j]);
					if (index == -1)
						break;

					wordCharArray[index] = '\u0000';

					if (j == word.length() - 1) {
						anagramList.add(allWords.get(i));
					}
				}
			}
		}

		private void dump() {
			System.out.println("Word: " + this.word);
			System.out.println("Anagrams[" + anagramList.size() + "]: ");
			for (int i = 0; i < anagramList.size(); i++) {
				System.out.println("\t" + anagramList.get(i));
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

	private static void readAllWords(File file) {
		allWords = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				line = line.replaceAll("[-+.^:,?!'`\"]", "").trim().toUpperCase();
				for (String s : line.split(" ")) {
					if (!allWords.contains(s)) {
						allWords.add(s);
					}
				}
			}
			br.close();
		} catch (IOException e) {
		}
	}

	public static void main(String[] args) {
		File file = new File(FILE_PATH);
		if (file.exists()) {
			readAllWords(file);
			int count = 0;
			for (int i = 0; i < allWords.size(); i++) {
				Anagrams anagram = new Anagrams(allWords.get(i));
				anagram.findMatches();
				if (anagram.anagramList.size() > 0) {
					count++;
					anagram.dump();
					System.out.println();
				}
			}
			System.out.println("TOTAL UNIQUE WORDS READ: " + allWords.size());
			System.out.println("WORDS WITH ANAGRAMS: " + count);
		} else {
			System.out.println("File Does Not Exist: " + file.getPath());
		}
	}

}
