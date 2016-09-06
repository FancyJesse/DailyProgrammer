package challenge15.intermediate;

import java.util.Random;

public class Main {

	private static class FleaGrid {
		private int[][] grid;
		private final int SIZE = 30;

		public FleaGrid() {
			this.grid = new int[SIZE][SIZE];

			for (int i = 0; i < SIZE; i++)
				for (int j = 0; j < SIZE; j++)
					this.grid[i][j] = 1;
		}

		public void jump(int times) {
			System.out.println("Ringing The Bell " + times + " Times ...");
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					while (true) {
						try {
							switch (rdmDirection()) {
							case 0: // top-left
								this.grid[i - 1][j - 1]++;
								break;
							case 1: // top
								this.grid[i - 1][j]++;
								break;
							case 2: // top-right
								this.grid[i - 1][j + 1]++;
								break;
							case 3: // left
								this.grid[i][j - 1]++;
								break;
							case 4: // right
								this.grid[i][j + 1]++;
								break;
							case 5: // bottom-left
								this.grid[i + 1][j - 1]++;
								break;
							case 6: // bottom
								this.grid[i + 1][j]++;
								break;
							case 7: // bottom-right
								this.grid[i + 1][j + 1]++;
								break;
							default:
								System.out.println("Not Suppose to Happen.");
								System.exit(1);
							}

							this.grid[i][j]--;
							break;

						} catch (IndexOutOfBoundsException e) {
						}
					}
				}
			}
		}

		private int rdmDirection() {
			return new Random().nextInt(8);
		}

		private void display() {
			int highest = this.grid[0][0];
			int empty = 0, fleas = 0;

			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					System.out.print(this.grid[i][j] + " ");
					if (this.grid[i][j] > highest) {
						highest = this.grid[i][j];
					}
					if (this.grid[i][j] == 0) {
						empty++;
					}
					fleas += this.grid[i][j];
				}
				System.out.println();
			}
			System.out.println("Flea Count: " + fleas);
			System.out.println("Highest Occupied Square: " + highest);
			System.out.println("Empty Squares: " + empty);
			System.out.println();
		}

	}

	public static void main(String[] args) {
		FleaGrid fleaGrid = new FleaGrid();
		fleaGrid.display();
		fleaGrid.jump(50);
		fleaGrid.display();
	}

}