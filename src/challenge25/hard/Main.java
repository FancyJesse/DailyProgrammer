package challenge25.hard;

public class Main {

	private static class QueenGrid {
		private final int SIZE = 25;
		private char[][] grid;

		public QueenGrid() {
			this.grid = new char[SIZE][SIZE];

			for (int i = 0; i < SIZE; i++)
				for (int j = 0; j < SIZE; j++)
					this.grid[i][j] = '.';
		}

		public boolean setQueens(int queenCount) {
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					if (this.grid[i][j] == '.') {
						markQueenLine(i, j);
						queenCount--;
					}
					if (queenCount == 0)
						return true;
				}
			}
			return false;
		}

		public void display() {
			int qcnt = 0;
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					System.out.print(this.grid[i][j] + " ");
					if (this.grid[i][j] == 'Q')
						qcnt++;
				}
				System.out.println();
			}
			System.out.println("Queen Count Found: " + qcnt);
		}

		private void markQueenLine(int i, int j) {
			for (int row_col = 0; row_col < SIZE; row_col++) {
				this.grid[row_col][j] = 'x';
				this.grid[i][row_col] = 'x';
			}

			int temp_i, temp_j;

			temp_i = i;
			temp_j = j;
			while (temp_i < SIZE && temp_j < SIZE)
				this.grid[temp_i++][temp_j++] = 'x';

			temp_i = i;
			temp_j = j;
			while (temp_i <= 0 && temp_j >= 0)
				this.grid[temp_i--][temp_j--] = 'x';

			temp_i = i;
			temp_j = j;
			while (temp_i >= 0 && temp_j < SIZE)
				this.grid[temp_i--][temp_j++] = 'x';

			temp_i = i;
			temp_j = j;
			while (temp_i < SIZE && temp_j >= 0)
				this.grid[temp_i++][temp_j--] = 'x';

			this.grid[i][j] = 'Q';
		}

	}

	public static void main(String[] args) {
		boolean success;
		int queenCnt = 10;

		QueenGrid queenGrid = new QueenGrid();
		success = queenGrid.setQueens(queenCnt);
		queenGrid.display();

		System.out.println("\nSet " + queenCnt + " Queens Possible: " + success);
	}

}