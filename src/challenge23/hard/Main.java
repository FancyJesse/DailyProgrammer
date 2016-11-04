package challenge23.hard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * INCOMPLETE
 *
 */

public class Main {

	private static final String[] COMPARATORS = { "a-z", "A-Z", "0-9", "ASCII-order", "ASCII-order-ignore-case",
			"reverse-ASCII-order", "reverse-ASCII-order-ignore-case" };

	private static String getInput(String prompt) {
		while (true) {
			try {
				System.out.print(prompt + "\n> ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				return br.readLine();
			} catch (Exception e) {
				System.out.println("Something Went Wrong. Try Again.");
			}
		}
	}

	private static void displayWithComparators(int comparatorIndex, List<String> list) {

		System.out.println("Sorted With " + COMPARATORS[comparatorIndex]);

		switch (comparatorIndex) {
		case 0:
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			Collections.sort(list);
			break;
		case 4:
			Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
			break;
		case 5:
			Collections.sort(list);
			Collections.reverse(list);
			break;
		case 6:
			Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
			Collections.reverse(list);
			break;
		}

		System.out.println(list.toString());

	}

	public static void main(String[] args) {

		int comparatorCount;
		List<Integer> comparatorIndexList;
		List<String> stringList;

		while (true)
			try {
				comparatorCount = Integer.parseInt(getInput("How Many Comparators?"));
				break;
			} catch (Exception e) {
				System.out.println("Not a Number. Try Again.");
			}

		comparatorIndexList = new ArrayList<>();
		while (comparatorIndexList.size() != comparatorCount) {
			String comparatorInput = getInput("Comparator " + (comparatorIndexList.size() + 1) + ":");

			int index = (Arrays.asList(COMPARATORS).indexOf(comparatorInput));
			if (index != -1)
				comparatorIndexList.add(index);
			else
				System.out.println("Invalid Comparator. Try Again.");
		}

		stringList = new ArrayList<>();
		System.out.println("Enter Items To Sort -- [!q] To Quit");
		while (true) {
			String itemInput = getInput("");
			if (itemInput.equals("!q"))
				break;
			stringList.add(itemInput);
		}

		for (int comparatorIndex : comparatorIndexList)
			displayWithComparators(comparatorIndex, stringList);

	}

}
