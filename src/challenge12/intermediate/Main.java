package challenge12.intermediate;

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
			} catch (IOException e) {
				System.out.println("That is not a Number. Try Again.");
			}
		}
	}

	private static void factor(int number) {
		int ans, div;

		if (number % 2 == 0) {
			div = 2;
		}
		// replace with prime finder
		else if (number % 3 == 0) {
			div = 3;
		} else if (number % 5 == 0) {
			div = 5;
		} else if (number % 7 == 0) {
			div = 7;
		} else if (number % 11 == 0) {
			div = 11;
		} else {
			div = number;
		}

		System.out.print(div);
		ans = number / div;
		if (ans != 1) {
			System.out.print(" * ");
			factor(ans);
		}
	}

	public static void main(String[] args) {
		int input;

		input = getInput("Enter a Number to Factor:");
		System.out.print(input + " = ");
		factor(input);
	}

}