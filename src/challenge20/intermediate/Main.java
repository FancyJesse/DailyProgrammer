package challenge20.intermediate;

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

	private static void calculateAge(int age) {
		System.out.println("\nYou are " + age + " years old.");
		System.out.println("You are " + age * 12 + " months old.");
		System.out.println("You are " + age * 365 + " days old.");
		System.out.println("You are " + age * 365 * 24 + " hours old.");
		System.out.println("You are " + age * 365 * 24 * 60 + " minutes old.");
	}

	public static void main(String[] args) {
		calculateAge(getInput("How old are you?"));
	}

}