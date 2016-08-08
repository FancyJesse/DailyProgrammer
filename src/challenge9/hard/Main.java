package challenge9.hard;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		ArrayList<Integer> previousRow = new ArrayList<>();
		ArrayList<Integer> newRow = new ArrayList<>();
		int MAX_LINES = 40;
		int count, buffer;

		previousRow.add(1);
		for (int line = 0; line < MAX_LINES; line++) {
			System.out.println(Arrays.toString(previousRow.toArray()));
			count = 1;
			for (int i = 0; i < previousRow.size(); i++) {
				buffer = previousRow.get(i);
				if (i + 1 < previousRow.size() && buffer == previousRow.get(i + 1)) {
					count++;
					continue;
				}
				newRow.add(count);
				newRow.add(previousRow.get(i));
				count = 1;
			}
			previousRow = new ArrayList<>(newRow);
			newRow.clear();
		}
	}

}
