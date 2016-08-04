package challenge8.hard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

	private static void printArray(long[] line) {
		for (int i = 0; i < line.length; i++) {
			System.out.print(line[i] + " ");
		}
		System.out.println();
	}

	private static long getPascalTriangleValue(int row, int column) {
		long[] previousRow, currentRow;

		currentRow = new long[] { 1 };
		printArray(currentRow);
		previousRow = currentRow;

		for (int i = 2; i <= row; i++) {
			currentRow = new long[i];
			currentRow[0] = 1;
			currentRow[i - 1] = 1;

			for (int j = 0; j <= i - 3; j++) {
				currentRow[j + 1] = previousRow[j] + previousRow[j + 1];
			}
			printArray(currentRow);
			previousRow = currentRow;

		}

		return currentRow[column - 1];
	}

	public static void main(String[] args) {
		int row, column;
		long value;

		System.out.println("Pascal's Triangle Value");
		row = getInput("Which row?");
		column = getInput("Which column?");

		try {
			value = getPascalTriangleValue(row, column);
			if (value < 0) {
				System.out.println("OVERFLOW!");
			}
			System.out.println("Value: " + value);
		} catch (Exception e) {
			System.out.println("Does Not Exist");
		}
	}

}
