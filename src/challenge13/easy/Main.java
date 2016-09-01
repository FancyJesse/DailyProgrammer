package challenge13.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

	private static int getDayofTheYear(int day, int month, int year) {
		try {
			SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			newDateFormat.setLenient(false);
			Date MyDate = newDateFormat.parse(day + "/" + month + "/" + year);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(MyDate);
			return calendar.get(Calendar.DAY_OF_YEAR);
		} catch (Exception e) {
			System.out.println("Invalid Date Format");
			return -1;
		}
	}

	public static void main(String[] args) {
		int day, month, year;
		int dayNum;

		day = getInput("Enter a Day:");
		month = getInput("Enter a Month:");
		year = getInput("Enter a Year:");

		dayNum = getDayofTheYear(day, month, year);
		if (dayNum > 0)
			System.out.println("The Day of the Year is: " + dayNum);

	}

}
