package challenge21.easy;

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
				System.out.println("Something Went Wrong. Try Again.");
			}
		}
	}

	// finds next number that contains all digits in num
	private static void findNextNumber(int num) {
		char[] cDigits = String.valueOf(num).toCharArray();

		// set limit to 9x(length)
		int limit = Integer.parseInt(new String(new char[cDigits.length]).replace("\0", "9"));

		int nextNum = -1;
		for (int i = num + 1; i <= limit; i++) {

			String sNumber = String.valueOf(i);
			for (char c : cDigits) {
				String sChar = String.valueOf(c);

				if (sNumber.contains(sChar))
					sNumber = sNumber.replaceFirst(sChar, "F");
				else
					break;
			}

			if (sNumber.matches("[F]+")) {
				nextNum = i;
				break;
			}
		}

		if (nextNum != -1)
			System.out.println("\nNext Number: " + nextNum);
		else
			System.out.println("\nUnable To Find Next Number For: " + num);

	}

	public static void main(String[] args) {
		int number;

		number = getInput("Enter a Number:");
		findNextNumber(number);
	}

}
