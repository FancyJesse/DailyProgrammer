package challenge27.hard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main {

	private final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	private static String getInput(String prompt) {
		while (true) {
			try {
				System.out.print(prompt + "\n> ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				return br.readLine();
			} catch (Exception e) {
				System.out.println("Something Went Wrong. Try Again.");
			}
		}
	}

	private static Calendar getCalendar() {
		String input;
		while (true) {
			try {
				input = getInput("Enter a date and time (YYYY-MM-DD hh:mm:ss):");
				Calendar c = Calendar.getInstance();
				c.setTime(format.parse(input));
				return c;
			} catch (Exception e) {
				System.out.println("Invalid Date Time Format. Try Again.\n");
			}
		}
	}

	private static Calendar modifyCalendar(Calendar c) {
		int value;
		while (true) {
			try {
				value = Integer.parseInt(getInput("Enter value to add:"));
				break;
			} catch (Exception e) {
				System.out.println("Not a Number. Try Again.\n");
			}
		}

		boolean loop = true;
		while (loop) {
			switch (getInput("Enter unit of time (year, month, week, day, hour, minute, second):")) {
			case "year":
				c.add(Calendar.YEAR, value);
				loop = false;
				break;
			case "month":
				c.add(Calendar.MONTH, value);
				loop = false;
				break;
			case "week":
				c.add(Calendar.WEEK_OF_YEAR, value);
				loop = false;
				break;
			case "day":
				c.add(Calendar.DAY_OF_YEAR, value);
				loop = false;
				break;
			case "hour":
				c.add(Calendar.HOUR, value);
				loop = false;
				break;
			case "minute":
				c.add(Calendar.MINUTE, value);
				loop = false;
				break;
			case "second":
				c.add(Calendar.SECOND, value);
				loop = false;
				break;
			default:
				System.out.println("Invalid Option. Try Again.\n");
			}
		}

		return c;
	}

	public static void main(String[] args) {
		Calendar calendar;
		calendar = getCalendar();
		calendar = modifyCalendar(calendar);
		System.out.println("New date and time is " + format.format(calendar.getTime()));
	}

}
