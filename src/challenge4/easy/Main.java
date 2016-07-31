package challenge4.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Main {

	private static int getInput(String prompt) {
		int num;
		while (true) {
			try {
				System.out.print(prompt + "\n> ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

				num = Integer.parseInt(br.readLine());
				if (num > 0)
					return num;
				else
					System.out.print("Value Must be Greater Than Zero.");
			} catch (IOException | NumberFormatException e) {
				System.out.println("That is not a Number.");
			}
			System.out.println(" Please Try Again.");
		}
	}

	private static String[] generatePasswords(int pwCount, int pwLength) {
		String charBank = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789!@#$%^&*()_+=?";
		Random rdm = new Random();
		String[] passwords = new String[pwCount];

		for (int i = 0; i < pwCount; i++) {
			StringBuilder password = new StringBuilder(pwLength);
			for (int j = 0; j < pwLength; j++) {
				password.append(charBank.charAt(rdm.nextInt(charBank.length())));
			}
			passwords[i] = password.toString();
		}

		return passwords;
	}

	public static void main(String[] args) {
		int pwCount, pwLength;
		String passwords[];

		pwCount = getInput("How many passwords would you like to generate?");
		pwLength = getInput("What is the character length of the passwords?");

		passwords = generatePasswords(pwCount, pwLength);

		System.out.println("Passwords Generated at length " + pwLength + ":");
		for (String s : passwords) {
			System.out.println(s);
		}
	}

}