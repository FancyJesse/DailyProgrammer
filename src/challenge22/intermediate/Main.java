package challenge22.intermediate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class Main {

	/*
	 * Randomly creates path to goal Experiment sizes by changing values:
	 * MAX_GRID_SIZE and MAX_TRAVEL_DISTANCE
	 * 
	 */
	private static class MazeGame {
		private final int MAX_GRID_SIZE = 20;
		private final int MAX_TRAVEL_DISTANCE = 5;

		private final char PLAYER = '+';
		private final char WALL = '#';
		private final char GOAL = '@';

		private char[][] grid;
		private int posRow, posCol, movementCount, invalidMoves;
		private long startTime;

		public MazeGame() {
			resetGrid();
			createRandomPath();
		}

		private void resetGrid() {
			this.grid = new char[MAX_GRID_SIZE][MAX_GRID_SIZE];

			for (int i = 0; i < MAX_GRID_SIZE; i++)
				for (int j = 0; j < MAX_GRID_SIZE; j++)
					this.grid[i][j] = WALL;
		}

		private void createRandomPath() {
			Random rdm = new Random();

			// starting point
			int row = 0;
			int col = rdm.nextInt(MAX_GRID_SIZE) + 1;
			if (col >= MAX_GRID_SIZE - 1)
				col = MAX_GRID_SIZE - 2;
			this.grid[row][col] = PLAYER;

			this.posRow = row;
			this.posCol = col;

			// create path
			while (true) {
				int distance;

				// down
				distance = rdm.nextInt(MAX_TRAVEL_DISTANCE) + 1;
				if (row + distance > MAX_GRID_SIZE - 1)
					distance = MAX_GRID_SIZE - row - 1;
				for (int i = 0; i < distance; i++) {
					this.grid[++row][col] = ' ';
				}

				// check if end reached
				if (row == MAX_GRID_SIZE - 1) {
					this.grid[row][col] = GOAL;
					break;
				}

				// true ? left:right
				distance = rdm.nextInt(MAX_TRAVEL_DISTANCE) + 1;
				if (rdm.nextBoolean()) {
					if (col - distance < 0)
						distance = col;
					for (int i = 0; i < distance; i++) {
						if (col - 1 == 0)
							break;
						this.grid[row][--col] = ' ';
					}
				} else {
					if (col + distance > MAX_GRID_SIZE - 1)
						distance = MAX_GRID_SIZE - col - 1;
					for (int i = 0; i < distance; i++) {
						if (col + 1 == MAX_GRID_SIZE - 1)
							break;
						this.grid[row][++col] = ' ';
					}
				}

				// up
				distance = rdm.nextInt(MAX_TRAVEL_DISTANCE);
				if (row - distance < 0)
					distance = row;
				for (int i = 0; i < distance; i++) {
					if (row - 1 == 0)
						break;
					this.grid[--row][col] = ' ';
				}

			}

		}

		private void display() {
			for (int i = 0; i < MAX_GRID_SIZE; i++) {
				for (int j = 0; j < MAX_GRID_SIZE; j++) {
					System.out.print(this.grid[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();
		}

		private void clearScreen() {
			for (int i = 0; i < MAX_GRID_SIZE; i++)
				System.out.println();
		}

		private void results() {
			long resultTime = System.currentTimeMillis() - this.startTime;

			display();
			System.out.println("\n\nYOU WIN!");
			System.out.println("Time: " + resultTime / 1000 + " seconds");
			System.out.println("Times Moved: " + this.movementCount);
			System.out.println("Invalid Moves: " + this.invalidMoves);
			System.out.println("\n\nGAME OVER.\nTHANKS FOR PLAYING!");
		}

		// add additional conditions when game develops
		private boolean checkStatus() {
			boolean keepPlaying;
			keepPlaying = this.posRow != MAX_GRID_SIZE - 1;
			return keepPlaying;
		}

		private void getInput(String prompt) {

			System.out.print(prompt + "\n> ");
			while (true) {
				try {

					// requires Enter to continue;
					BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					String input = br.readLine().toUpperCase().trim();

					switch (input) {
					case "W":
						if (this.posRow - 1 >= 0 && this.grid[this.posRow - 1][this.posCol] != WALL) {
							this.grid[this.posRow][this.posCol] = ' ';
							this.grid[--this.posRow][this.posCol] = PLAYER;
							this.movementCount++;
							return;
						}
						break;
					case "S":
						if (this.posRow + 1 < MAX_GRID_SIZE && this.grid[this.posRow + 1][this.posCol] != WALL) {
							this.grid[this.posRow][this.posCol] = ' ';
							this.grid[++this.posRow][this.posCol] = PLAYER;
							this.movementCount++;
							return;
						}
						break;
					case "A":
						if (this.posCol - 1 >= 0 && this.grid[this.posRow][this.posCol - 1] != WALL) {
							this.grid[this.posRow][this.posCol] = ' ';
							this.grid[this.posRow][--this.posCol] = PLAYER;
							this.movementCount++;
							return;
						}
						break;
					case "D":
						if (this.posCol + 1 < MAX_GRID_SIZE && this.grid[this.posRow][this.posCol + 1] != WALL) {
							this.grid[this.posRow][this.posCol] = ' ';
							this.grid[this.posRow][++this.posCol] = PLAYER;
							this.movementCount++;
							return;
						}
						break;
					}

					System.out.println("Invalid Input. Try Again.");
					this.invalidMoves++;

				} catch (Exception e) {
					System.out.println("Something Went Wrong. Try Again.");
				}
			}
		}

		private void play() {
			boolean playing = true;

			this.movementCount = 0;
			this.invalidMoves = 0;
			this.startTime = System.currentTimeMillis();
			while (playing) {
				display();
				getInput("[W]: UP\n[S]: DOWN\n[A]: LEFT\n[D]RIGHT");
				playing = checkStatus();
				clearScreen();
			}
			results();
		}

	}

	public static void main(String[] args) {
		MazeGame mazeGame = new MazeGame();
		mazeGame.play();
	}

}
