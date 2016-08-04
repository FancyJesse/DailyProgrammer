package challenge8.intermediate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	private static final String[] onesNames = { "", "one", "two", "three", "four", "five", "six", "seven", "eight",
			"nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen",
			"nineteen" };

	private static final String[] tensNames = { "", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy",
			"eighty", "ninety" };

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

	private static void sayValue(int value) {
		String[] reversedStrList = new StringBuilder(String.valueOf(value)).reverse().toString().split("(?<=\\G...)");
		ArrayList<Integer> setOf3 = new ArrayList<>();
		for (int i = reversedStrList.length - 1; i >= 0; i--) {
			reversedStrList[i] = new StringBuilder(reversedStrList[i]).reverse().toString();
			setOf3.add(Integer.parseInt(reversedStrList[i]));
		}

		System.out.println(setOf3.toString());

		StringBuilder sb = new StringBuilder();
		int size = setOf3.size();
		for (Integer set : setOf3) {

			if (set > 99) {
				sb.append(onesNames[Integer.parseInt(String.valueOf(set).substring(0, 1))]);
				sb.append("-hundred ");
			}
			if ((set % 100) < 20) {
				sb.append(onesNames[set % 100]);
			} else {
				sb.append(tensNames[(set % 100) / 10]);
				if ((set % 10) != 0)
					sb.append("-" + onesNames[set % 10]);
			}

			if (set > 0)
				switch (size) {
				case 2:
					sb.append("-thousand ");
					break;
				case 3:
					sb.append("-million ");
					break;
				case 4:
					sb.append("-billion ");
					break;
				}
			size--;
		}

		System.out.println(sb.toString());

	}

	public static void main(String[] args) {
		int num;
		num = getInput("Enter Value:");
		sayValue(num);
	}

}
