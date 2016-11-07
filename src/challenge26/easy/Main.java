package challenge26.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

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

	private static String[] removeDuplicate(String word) {
		StringBuilder wordSb = new StringBuilder(word);
		StringBuilder dupeSb = new StringBuilder();

		for (int i = wordSb.length() - 1; i > 0; i--) {
			if (wordSb.charAt(i) == wordSb.charAt(i - 1)) {
				dupeSb.append(word.charAt(i));
				wordSb.deleteCharAt(i);
			}
		}

		return new String[] { wordSb.toString(), dupeSb.reverse().toString() };
	}

	public static void main(String[] args) {
		String input;
		input = getInput("Enter a String to Remove Duplicated:");
		for (String s : removeDuplicate(input))
			System.out.println(s);
	}

}
