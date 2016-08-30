package challenge11.hard;

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

	private static void printCalander(int day, int month, int year) {
		try {
			SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date MyDate = newDateFormat.parse(day + "/" + month + "/" + year);
			newDateFormat.applyPattern("EEEE d MMM yyyy");
			System.out.println(newDateFormat.format(MyDate));

			try {
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.DATE, 1);
				cal.set(Calendar.MONTH, month);
				cal.set(Calendar.YEAR, year);

				int dayCounter = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
				int maxDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				System.out.println("\tSun\tMon\tTus\tWed\tThu\tFri\tSat");

				for (int i = 0; i < dayCounter; i++) {
					System.out.print("\t");
				}

				for (int i = 1; i <= maxDays + 1; i++) {
					System.out.print("\t");
					if (i == day)
						System.out.print("[" + i + "]");
					else {
						if (i < 10)
							System.out.print(" ");
						
							System.out.print(i);
					}

					dayCounter++;
					if (dayCounter > 6) {
						System.out.println();
						dayCounter = 0;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("Invalid Date Format");
		}
	}

	public static void main(String[] args) {
		int day, month, year;

		day = getInput("Enter a Day:");
		month = getInput("Enter a Month:");
		year = getInput("Enter a Year:");

		printCalander(day, month, year);
	}

}
