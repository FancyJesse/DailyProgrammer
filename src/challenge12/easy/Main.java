package challenge12.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private static String getInput(String prompt) {
		while (true) {
			try {
				System.out.print(prompt + "\n> ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				return br.readLine();
			} catch (IOException e) {
				System.out.println("Something Went Wrong.");
				return "";
			}
		}
	}

	private static void permutations(String prefix, String s) {
		int l = s.length();
		if (l == 0)
			System.out.println(prefix);
		else
			for (int i = 0; i < l; i++)
				permutations(prefix + s.charAt(i), s.substring(0, i) + s.substring(i + 1, l));
	}

	public static void main(String[] args) {
		String input;

		input = getInput("Enter a String to Permutate:");
		permutations("", input);
	}

}