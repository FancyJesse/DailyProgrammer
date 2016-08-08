package challenge10.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Main {

	private static String getInput(String prompt) {
		while (true) {
			try {
				System.out.print(prompt + "\n> ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				return br.readLine();
			} catch (IOException e) {
				return "";
			}
		}
	}

	public static void main(String[] args) {
		Pattern pattern = Pattern.compile("^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$");
		String phoneNumber;

		phoneNumber = getInput("Enter a Phone Number:");
		System.out.println("Phone Number is Valid: " + pattern.matcher(phoneNumber).matches());
	}

}
