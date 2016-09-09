package challenge19.hard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * INCOMPLETE
 * 
 * Work in progress
 * 
 */

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
		private ArrayList<Word> words;

		public Page() {
			this.words = new ArrayList<>();
		}

		public void addLine(String line) {
			for (String word : line.replaceAll("[^A-Za-z0-9 ]", "").trim().toUpperCase().split(" ")) {
				word = word.trim();
				if (word.isEmpty())
					continue;
				int index = this.words.indexOf(word);
				if (index > -1) {
					this.words.get(index).wordFound();
				} else
					this.words.add(new Word(word));
			}
		}

		public void dump() {

			// for (int i = words.size() - 1; i >= 0; i--)
			// if (words.get(i).getCnt() > 100)
			// words.remove(i);

			Collections.sort(words, new Comparator<Word>() {
				@Override
				public int compare(Word o1, Word o2) {
					return o1.getWord().compareTo(o2.getWord());
				}
			});

			for (Word word : this.words) {
				word.dump();
			}
		}

		private class Word {
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
				if (o instanceof String)
					return o.equals(this.getWord());
				return false;
			}

			@Override
			public int hashCode() {
				return this.getWord().hashCode();
			}

			@Override
			public String toString() {
				return this.word;
			}

			public String getWord() {
				return this.word;
			}

			public int getCnt() {
				return this.cnt;
			}

			public void wordFound() {
				this.cnt++;
			}

			public void dump() {
				System.out.println(this.word + '\t' + this.cnt);
			}
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < chapters.size(); i++)
			chapters.set(i, chapters.get(i).toUpperCase());

		countWords();

		for (int i = 0; i < pages.size(); i++) {
			System.out.println("\nPage " + (i + 1));
			pages.get(i).dump();
		}
	}

}
