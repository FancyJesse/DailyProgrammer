package challenge14.intermediate;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		int n = 100;
		ArrayList<Integer> numberList = new ArrayList<>();

		for (int i = 1; i <= n; i++) {
			numberList.add(i);
		}

		for (int i = 1; i <= n; i++) {
			for (int j = i; i <= j && (i + j + 2 * i * j) < n; j++) {
				numberList.set(i + j + 2 * i * j, -1);
			}
		}

		int count = 0;
		for (int i = 1; i < n; i++) {
			if (numberList.get(i) != -1) {
				System.out.print(2 * i + 1 + " ");
				if (++count == 10) {
					System.out.println();
					count = 0;
				}
			}
		}

	}

}