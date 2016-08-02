package challenge6.hard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/*
 * Thanks to: https://www.youtube.com/watch?v=9KABcmczPdg
 * 	For details of game
 * 
 */

public class Main {

	private static int getInput(String prompt) {
		while (true) {
			try {
				System.out.print(prompt + "\n> ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				return Integer.parseInt(br.readLine());
			} catch (NumberFormatException | IOException e) {
				System.out.println("That Is Not A Number. Try Again.");
			}
		}
	}

	public static class Nim {
		private int marbels;
		private boolean playing;

		private void playerMove() {
			int marbelsToTake;
			while (true) {
				marbelsToTake = getInput("How Many Marbels Will You Take?");
				if (marbelsToTake > 3 || marbelsToTake < 1) {
					System.out.println("Stop Trying To Cheat.");
				} else if (marbelsToTake >= marbels) {
					System.out.println("You Take The Remaining " + marbels + " Marbels.\nYOU WIN!");
					playing = false;
					break;
				} else {
					System.out.println("You Take  " + marbelsToTake + " Marbels.");
					break;
				}
			}
			marbels -= marbelsToTake;
		}

		private void cpuMove() {
			int marbelsToTake;
			switch (marbels) {
			case 1:
			case 2:
			case 3:
				marbelsToTake = marbels;
				break;
			case 5:
				marbelsToTake = 1;
			case 6:
				marbelsToTake = 2;
			case 7:
				marbelsToTake = 3;
			default:
				marbelsToTake = new Random().nextInt(3) + 1;
			}

			if (marbelsToTake == marbels) {
				System.out.println("CPU Takes The Remaining " + marbels + " Marbels.\nYOU LOSE!");
				playing = false;
			} else {
				System.out.println("CPU Takes  " + marbelsToTake + " Marbels.");
			}

			marbels -= marbelsToTake;
		}

		public void startGame(int marbels) {
			this.marbels = marbels;
			this.playing = true;

			System.out.println();
			boolean playerTurn = new Random().nextBoolean();
			if (playerTurn)
				System.out.println("You Go First.");
			else
				System.out.println("CPU Goes First.");

			while (playing) {
				System.out.println("\nThere Are " + this.marbels + " Marbels Left");

				if (playerTurn)
					playerMove();
				else
					cpuMove();

				playerTurn = !playerTurn;
			}

		}
	}

	public static void main(String[] args) {
		int startingMarbels;

		while ((startingMarbels = getInput("How Many Marbels In The Game?")) < 10) {
			System.out.println("Two Few Marbels. Must Be 10 or Greater.");
		}

		Nim nim = new Nim();
		nim.startGame(startingMarbels);

	}

}
