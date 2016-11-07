package challenge25.intermediate;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	private static int getInput(String prompt) {
		while (true) {
			try {
				System.out.print(prompt + "\n> ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				return Integer.parseInt(br.readLine());
			} catch (Exception e) {
				System.out.println("That is not an Integer. Try Again.");
			}
		}
	}

	private static void binaryRep(int number) {
		if (number > 0) {
			binaryRep(number / 2);
			System.out.print(number % 2);
		}
	}

	public static void main(String[] args) {
		int number;

		number = getInput("Enter an Integer:");
		System.out.print("Binary Rep: ");
		binaryRep(number);
		System.out.println("\nEasy Binary: " + Integer.toBinaryString(number));
	}

}
