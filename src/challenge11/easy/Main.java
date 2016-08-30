package challenge11.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

	private static int getInput(String prompt) {
		while (true) {
			try {
				System.out.print(prompt + "\n> ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				return Integer.parseInt(br.readLine());
			} catch (IOException e) {
				System.out.println("That is not a Number. Try again.");
			}
		}
	}

	private static String getDayOfTheWeek(int day, int month, int year) {
		try {
			SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date MyDate = newDateFormat.parse(day + "/" + month + "/" + year);
			newDateFormat.applyPattern("EEEE");
			return newDateFormat.format(MyDate);
		} catch (Exception e) {
			System.out.println("Invalid Date Format");
			return null;
		}
	}

	public static void main(String[] args) {
		int day, month, year;
		String dayString;

		day = getInput("Enter a Day:");
		month = getInput("Enter a Month:");
		year = getInput("Enter a Year:");

		dayString = getDayOfTheWeek(day, month, year);
		if (dayString != null)
			System.out.println("The Day of the Week is: " + dayString);

	}

}
