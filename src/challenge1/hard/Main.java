package challenge1.hard;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	private static class GuessingGame {
		private int number;
		private int guesses;

		public void start() {
			guesses = 0;
			setNumber();
			startGuessing();
		}

		private void setNumber() {
			while (true) {
				try {
					System.out.print("Choose a Number Between 0-100\n> ");
					BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					number = Integer.parseInt(br.readLine());
					if (0 <= number && number <= 100)
						break;
				} catch (Exception e) {
				}
				System.out.println("Invalid Input. Try Again.");
			}
		}

		private int getHighLow() {
			int input;
			while (true) {
				try {
					System.out.print("0. Too Low\n1. Too High\n> ");
					BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					input = Integer.parseInt(br.readLine());
					if (input == 0 || input == 1)
						return input;
				} catch (Exception e) {
				}
				System.out.println("Invalid Input. Try Again.");
			}
		}

		private void startGuessing() {
			int min = 0, max = 100, guess;

			while (true) {
				if ((max == min)) {
					System.out.println("Stop Cheating.");
					break;
				}
				guess = (min + max) / 2;
				System.out.println("CPU Guesses: " + guess);
				// System.out.println("Min: " + min + "\tMax: " + max);

				if (checkGuess(guess)) {
					break;
				}

				switch (getHighLow()) {
				case 0:
					min = guess;
					break;
				case 1:
					max = guess;
					break;
				default:
					System.out.println("what?");
				}

			}

		}

		private boolean checkGuess(int guess) {
			guesses++;
			if (guess == number) {
				System.out.println("CPU Guessed Correctly After " + guesses + " Guesses.");
				return true;
			}
			return false;
		}

	}

	public static void main(String[] args) {
		GuessingGame guessingGame = new GuessingGame();
		guessingGame.start();
	}

}
