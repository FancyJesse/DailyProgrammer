package challenge18.hard;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/*
 * INCOMPLETE
 * 
 */

public class Main {
	private static final String output = "./src/challenge18/hard/spiral.txt";
	private static final char block = '\u25A0';

	private static void squareSpriralToFile(int size) {
		char[][] grid = new char[size][size];
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				grid[i][j] = ' ';

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i % 2 == 0)
					grid[i][j] = block;
				// TODO
			}
		}

		try {
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output), "UTF-8"));

			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++)
					out.write(grid[i][j]);
				out.write("\n");
			}

			out.close();
			System.out.println("Success.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		int size = 21;
		squareSpriralToFile(size);
	}

}