package challenge19.hard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
	private static final String bookUrl = "http://www.gutenberg.org/cache/epub/1661/pg1661.txt";
	private static List<String> chapters = Arrays.asList("I.", "A Scandal in Bohemia", "II.", "The Red-headed League",
			"III.", "A Case of Identity", "IV.", "The Boscombe Valley Mystery", "V.", "The Five Orange Pips", "VI.",
			"The Man with the Twisted Lip", "VII.", "The Adventure of the Blue Carbuncle", "VIII.",
			"The Adventure of the Speckled Band", "IX.", "The Adventure of the Engineer's Thumb", "X.",
			"The Adventure of the Noble Bachelor", "XI.", "The Adventure of the Beryl Coronet", "XII.",
			"The Adventure of the Copper Beeches");
	private static ArrayList<Page> pages;

	private static void countWords() {
		try {
			URL url = new URL(bookUrl);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

			String line;
			boolean start = false;
			int lineCnt = 0;
			Page page = null;
			pages = new ArrayList<>();
			while ((line = br.readLine()) != null) {
				if (line.contains("End of the Project Gutenberg")) {
					break;
				}

				if (!start && line.startsWith("ADVENTURE")) {
					start = true;
					page = new Page();
					lineCnt = 0;
				}

				if (start) {
					if (!(line.contains("ADVENTURE") || chapters.contains(line))) {
						lineCnt++;
						if (lineCnt > 40) {
							pages.add(page);
							page = new Page();
							lineCnt = 0;
						}
						page.addLine(line);
					}
				}
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static class Page {
		private ArrayList<Word> wordList;

		public Page() {
			this.wordList = new ArrayList<>();
		}

		public ArrayList<Word> getWordList() {
			return new ArrayList<>(this.wordList);
		}

		public void addLine(String line) {
			for (String word : line.replaceAll("[^A-Za-z0-9 ]", "").trim().toUpperCase().split(" ")) {
				word = word.trim();
				if (word.isEmpty())
					continue;
				int index = indexOfWord(this.wordList, word);
				if (index > -1) {
					this.wordList.get(index).incCnt();
				} else
					this.wordList.add(new Word(word));
			}
		}

		public void dump() {
			Collections.sort(this.wordList, WordComparator);
			Collections.reverse(this.wordList);

			for (Word word : this.wordList) {
				word.dump();
			}
		}
	}

	private static class Word {
		private String word;
		private int cnt;

		public Word(String word) {
			this.word = word;
			this.cnt = 1;
		}

		@Override
		public boolean equals(Object o) {
			if (o instanceof Word)
				return ((Word) o).getWord().equals(this.getWord());
			return false;
		}

		public String getWord() {
			return this.word;
		}

		public int getCnt() {
			return this.cnt;
		}

		public void incCnt(int c) {
			this.cnt += c;
		}

		public void incCnt() {
			this.cnt++;
		}

		public void dump() {
			System.out.println(this.word + '\t' + this.cnt);
		}
	}

	private static Comparator<Word> WordComparator = new Comparator<Word>() {
		@Override
		public int compare(Word o1, Word o2) {
			int result = Integer.compare(o1.getCnt(), o2.getCnt());
			if (result != 0)
				return result;
			else
				return o1.getWord().compareTo(o2.getWord());
		}
	};

	private static int indexOfWord(List<Word> wordList, String word) {
		for (int index = 0; index < wordList.size(); index++) {
			if (word.equals(wordList.get(index).getWord()))
				return index;
		}
		return -1;
	}

	private static void dumpWords() {
		ArrayList<Word> allWords = new ArrayList<>();
		for (Page page : pages) {
			for (Word word : page.getWordList()) {
				int index = allWords.indexOf(word);
				if (index == -1)
					allWords.add(word);
				else
					allWords.get(index).incCnt(word.getCnt());
			}
		}

		Collections.sort(allWords, WordComparator);
		Collections.reverse(allWords);

		for (Word word : allWords) {
			if (word.getCnt() > 100)
				continue;
			System.out.print(word.getWord() + "[" + word.getCnt() + "]: ");
			for (int page = 0; page < pages.size(); page++) {
				int index = indexOfWord(pages.get(page).getWordList(), word.getWord());
				if (index > -1) {
					System.out.print(page + "[" + pages.get(page).getWordList().get(index).getCnt() + "]" + " ");
				}
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < chapters.size(); i++)
			chapters.set(i, chapters.get(i).toUpperCase());

		countWords();

		// dump pages separately
		for (int i = 0; i < pages.size(); i++) {
			System.out.println("\nPage " + (i + 1));
			pages.get(i).dump();
		}

		dumpWords();

	}

}
