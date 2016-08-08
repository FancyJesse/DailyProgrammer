package challenge10.intermediate;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * Relates to challenge8.intermediate
 * 
 */

public class Main {

	private static final String[] onesNames = { "", "one", "two", "three", "four", "five", "six", "seven", "eight",
			"nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen",
			"nineteen" };

	private static final String[] tensNames = { "", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy",
			"eighty", "ninety" };

	private static String getValueString(int value) {
		String[] reversedStrList = new StringBuilder(String.valueOf(value)).reverse().toString().split("(?<=\\G...)");
		ArrayList<Integer> setOf3 = new ArrayList<>();
		for (int i = reversedStrList.length - 1; i >= 0; i--) {
			reversedStrList[i] = new StringBuilder(reversedStrList[i]).reverse().toString();
			setOf3.add(Integer.parseInt(reversedStrList[i]));
		}

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

		return sb.toString();

	}

	public static void main(String[] args) {
		String equation, left, right;
		int MAX = 40;

		for (int a = 0; a < MAX; a++) {
			for (int b = 0; b < MAX; b++) {
				for (int c = 0; c < MAX; c++) {
					for (int d = 0; d < MAX; d++) {
						if (a + b == c + d) {
							if ((a == c && b == d) || (a == d || b == c))
								continue;
							left = getValueString(a);
							left += " plus " + getValueString(b);
							right = getValueString(c);
							right += " plus " + getValueString(d);
							equation = left + " equals " + right;

							char[] leftLetters = left.replaceAll("[\\s]", "").toCharArray();
							char[] rightLetters = right.replaceAll("[\\s]", "").toCharArray();
							Arrays.sort(leftLetters);
							Arrays.sort(rightLetters);
							if (Arrays.equals(leftLetters, rightLetters))
								System.out.println(equation);
						}
					}
				}
			}

		}
	}

}
