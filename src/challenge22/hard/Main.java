package challenge22.hard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

/*
 * Heavily related to challenge22.intermediate
 * 
 */

public class Main {

	private static class MazeGame {
		private int MAX_GRID_SIZE;
		private int MAX_TRAVEL_DISTANCE;

		private final char PLAYER = '+';
		private final char WALL = '#';
		private final char GOAL = '@';

		private char[][] grid;
		private int posRow, posCol, movementCount, invalidMoves;
		private long startTime;

		public MazeGame() {
			MAX_GRID_SIZE = getInput("Max Grid Size:");
			MAX_TRAVEL_DISTANCE = MAX_GRID_SIZE / 4;
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

		private void results() {
			long resultTime = System.currentTimeMillis() - this.startTime;

			display();
			System.out.println("\nCPU FOUND THE EXIT!");
			System.out.println("Time: " + resultTime / 1000 + " seconds");
			System.out.println("Times Moved: " + this.movementCount);
			System.out.println("Invalid Moves: " + this.invalidMoves);
			System.out.println("\n\nGAME OVER.\nTHANKS FOR PLAYING!");
		}

		private boolean checkStatus() {
			boolean keepPlaying;
			keepPlaying = this.posRow != MAX_GRID_SIZE - 1;
			return keepPlaying;
		}

		private void randomMovement() {

			while (true) {

				switch (new Random().nextInt(4)) {
				case 0:
					if (this.posRow - 1 >= 0 && this.grid[this.posRow - 1][this.posCol] != WALL) {
						this.grid[this.posRow][this.posCol] = ' ';
						this.grid[--this.posRow][this.posCol] = PLAYER;
						this.movementCount++;
						return;
					}
					break;
				case 1:
					if (this.posRow + 1 < MAX_GRID_SIZE && this.grid[this.posRow + 1][this.posCol] != WALL) {
						this.grid[this.posRow][this.posCol] = ' ';
						this.grid[++this.posRow][this.posCol] = PLAYER;
						this.movementCount++;
						return;
					}
					break;
				case 2:
					if (this.posCol - 1 >= 0 && this.grid[this.posRow][this.posCol - 1] != WALL) {
						this.grid[this.posRow][this.posCol] = ' ';
						this.grid[this.posRow][--this.posCol] = PLAYER;
						this.movementCount++;
						return;
					}
					break;
				case 3:
					if (this.posCol + 1 < MAX_GRID_SIZE && this.grid[this.posRow][this.posCol + 1] != WALL) {
						this.grid[this.posRow][this.posCol] = ' ';
						this.grid[this.posRow][++this.posCol] = PLAYER;
						this.movementCount++;
						return;
					}
					break;
				}

				this.invalidMoves++;
			}
		}

		private void autoSolve() {
			boolean playing = true;

			this.movementCount = 0;
			this.invalidMoves = 0;
			this.startTime = System.currentTimeMillis();

			while (playing) {
				display();
				randomMovement();
				playing = checkStatus();
				System.out.println();
			}
			results();
		}

	}

	private static int getInput(String prompt) {
		while (true) {
			try {
				System.out.print(prompt + "\n> ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				return Integer.parseInt(br.readLine());
			} catch (Exception e) {
				System.out.println("Something Went Wrong. Try Again.");
			}
		}
	}

	public static void main(String[] args) {
		MazeGame mazeGame = new MazeGame();
		mazeGame.autoSolve();
	}

}
