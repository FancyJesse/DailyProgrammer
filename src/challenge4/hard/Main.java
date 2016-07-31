package challenge4.hard;

import java.util.Arrays;

public class Main {

	private static final int[][] NUMBER_SERIES = { { 4, 2, 8 }, { 6, 2, 12 }, { 6, 2, 3 }, { 9, 12, 108 },
			{ 4, 16, 64 } };

	public static void main(String[] args) {
		for (int[] series : NUMBER_SERIES) {
			System.out.println(Arrays.toString(series));
			Arrays.sort(series);

			if (series[0] + series[1] == series[2]) {
				System.out.println(series[0] + " + " + series[1] + " = " + series[2]);
				System.out.println(series[2] + " - " + series[0] + " = " + series[1]);
				System.out.println(series[2] + " - " + series[1] + " = " + series[0]);
			} else if (series[0] * series[1] == series[2]) {
				System.out.println(series[0] + " * " + series[1] + " = " + series[2]);
				System.out.println(series[2] + " / " + series[0] + " = " + series[1]);
				System.out.println(series[2] + " / " + series[1] + " = " + series[0]);
			}
			System.out.println();
		}
	}

}