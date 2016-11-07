package challenge27.intermediate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Calendar;

public class Main {

	private final static int ST_PATRICK_MONTH = 3;
	private final static int ST_PATRICK_DAY = 17;

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

	private static int stPatrickDay(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, ST_PATRICK_MONTH - 1, ST_PATRICK_DAY);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	public static void main(String[] args) {
		int year, dayLandsOn;
		year = getInput("Enter Year:");

		if (year <= 0) {
			System.out.println("Unable to Handle Year: " + year);
			return;
		}

		dayLandsOn = stPatrickDay(year);

		System.out.print("St. Patrick's Day " + year + " is on a ");
		switch (dayLandsOn) {
		case 1:
			System.out.println("Sunday.");
			break;
		case 2:
			System.out.println("Monday.");
			break;
		case 3:
			System.out.println("Tuesday.");
			break;
		case 4:
			System.out.println("Wednesday.");
			break;
		case 5:
			System.out.println("Thursday.");
			break;
		case 6:
			System.out.println("Friday.");
			break;
		case 7:
			System.out.println("Saturday.");
			break;
		default:
			System.out.println("CANNOT FIND. " + dayLandsOn);
		}

		int century = (year + 99) / 100;
		int cnt = 0;
		for (year = (century - 1) * 100 + 1; year < (century * 100) + 1; year++) {
			if (stPatrickDay(year) == 7)
				cnt++;
		}
		System.out.println("St. Patrick's Day Lands on a Saturday " + cnt + " Times in Century: " + century);

	}

}
