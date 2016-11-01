package challenge10.hard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	private static String getInput(String prompt) {
		while (true) {
			try {
				System.out.print(prompt + "\n> ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String word = br.readLine().toUpperCase().trim();

				if (word.replaceAll("[^A-Z]", "").length() > 2)
					return word;
				else
					System.out.println("Secret Word Is Too Short");

			} catch (Exception e) {
				System.out.println("Invalid Secret Word");
			}
		}
	}

	private static class Hangman {
		private char[] keyArray;
		private boolean gameOver;
		private ArrayList<Character> correctLetters;
		private ArrayList<Character> wrongLetters;

		public void start(String key) {
			this.keyArray = key.toCharArray();
			this.correctLetters = new ArrayList<>();
			this.wrongLetters = new ArrayList<>();
			this.gameOver = false;

			for (int i = 0; i < keyArray.length; i++) {
				if (Character.isAlphabetic(keyArray[i]))
					keyArray[i] = '_';
			}

			System.out.println("GAME START");
			showMan();

			char guess;
			boolean contains;
			while (!gameOver) {
				contains = false;
				guess = getChar("\n\nGuess a Letter:");
				for (int i = 0; i < key.length(); i++) {
					if (key.charAt(i) == guess) {
						keyArray[i] = guess;
						contains = true;
					}
				}
				if (contains) {
					if (!correctLetters.contains(guess))
						correctLetters.add(guess);
				} else {
					wrongLetters.add(guess);
				}

				showMan();

				if (!new String(keyArray).contains("_")) {
					System.out.println("YOU WIN!");
					gameOver = true;
				}
			}

		}

		private char getChar(String prompt) {
			char c;
			while (true) {
				try {
					System.out.print(prompt + "\n> ");
					BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					c = br.readLine().toUpperCase().charAt(0);

					if (Character.isLetter(c)) {
						if (wrongLetters.contains(c) || correctLetters.contains(c)) {
							System.out.println("You Already Guessed That Letter. Try Again.");
							continue;
						} else {
							return c;
						}
					}
				} catch (Exception e) {
				}
				System.out.println("That is Not a Valid Character. Try Again.");
			}
		}

		private void showMan() {
			switch (wrongLetters.size()) {
			case 0:
				System.out.println("     -----\\\\");
				System.out.println("     |    ||");
				System.out.println("          ||");
				System.out.println("          ||");
				System.out.println("          ||");
				System.out.println("          ||");
				System.out.println(" _________||");
				break;
			case 1:
				System.out.println("     -----\\\\");
				System.out.println("     |    ||");
				System.out.println("     O    ||");
				System.out.println("          ||");
				System.out.println("          ||");
				System.out.println("          ||");
				System.out.println(" _________||");
				break;
			case 2:
				System.out.println("     -----\\\\");
				System.out.println("     |    ||");
				System.out.println("     O    ||");
				System.out.println("     |    ||");
				System.out.println("     |    ||");
				System.out.println("          ||");
				System.out.println(" _________||");
				break;
			case 3:
				System.out.println("     -----\\\\");
				System.out.println("     |    ||");
				System.out.println("     O    ||");
				System.out.println("    /|    ||");
				System.out.println("     |    ||");
				System.out.println("          ||");
				System.out.println(" _________||");
				break;
			case 4:
				System.out.println("     -----\\\\");
				System.out.println("     |    ||");
				System.out.println("     O    ||");
				System.out.println("    /|\\   ||");
				System.out.println("     |    ||");
				System.out.println("          ||");
				System.out.println(" _________||");
				break;
			case 5:
				System.out.println("     -----\\\\");
				System.out.println("     |    ||");
				System.out.println("     O    ||");
				System.out.println("    /|\\   ||");
				System.out.println("     |    ||");
				System.out.println("    /     ||");
				System.out.println(" _________||");
				break;
			case 6:
				System.out.println("     -----\\\\");
				System.out.println("     |    ||");
				System.out.println("     O    ||");
				System.out.println("    /|\\   ||");
				System.out.println("     |    ||");
				System.out.println("    / \\   ||");
				System.out.println(" _________||");
				gameOver = true;
				System.out.println("\nYOU LOSE!");
				break;
			}

			if (!gameOver)
				showWordBank();
		}

		private void showWordBank() {
			System.out.println();
			for (int i = 0; i < keyArray.length; i++) {
				System.out.print(" " + keyArray[i]);
			}
			System.out.println();
			System.out.println("Wrong Guesses: " + Arrays.toString(wrongLetters.toArray()) + "\n");
		}
	}

	public static void main(String[] args) {
		String secretWord;

		secretWord = getInput("Input Secret Word to Start Game:");

		Hangman hangman = new Hangman();
		hangman.start(secretWord);

	}

}
