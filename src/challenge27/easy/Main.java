package challenge27.easy;

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
				System.out.println("Not a Number. Try Again.");
			}
		}
	}

	private static void calculateCentury(int year) {
		if (year <= 0) {
			System.out.println("No Century Below Year 0. You Entered Year: " + year);
			return;
		}
		System.out.println("Century: " + (year + 99) / 100);
	}

	private static void calculateLeapYear(int year) {
		if (year <= 0) {
			System.out.println("Cannot Calculate Leap Year For Year: " + year);
			return;
		}
		boolean isLeapYear;
		if (year % 4 != 0)
			isLeapYear = false;
		else if (year % 100 != 0)
			isLeapYear = true;
		else if (year % 400 != 0)
			isLeapYear = false;
		else
			isLeapYear = true;
		System.out.println("Leap year: " + isLeapYear);
	}

	public static void main(String[] args) {
		int year;
		year = getInput("Enter Year:");
		calculateCentury(year);
		calculateLeapYear(year);
	}

}
