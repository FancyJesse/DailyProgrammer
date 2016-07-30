package challenge1.intermediate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private static ScheduledHour[] schedule;

	private static class ScheduledHour {
		private int hour;
		private String activity;
		private String note;

		public ScheduledHour(int hour) {
			setHour(hour);
		}

		public String getHour() {
			return (hour < 10 ? "0" : "") + this.hour + ":00";
		}

		public String getActivity() {
			return activity;
		}

		public String getNote() {
			return note;
		}

		public void setHour(int hour) {
			this.hour = hour;
		}

		public void setNote(String note) {
			this.note = note;
		}

		public void setActivity(String activity) {
			this.activity = activity;
		}

	}

	private static void displaySchedule() {
		boolean displayed = false;
		System.out.println("===== RESERVED SCHEDULE =====");
		for (int i = 0; i < schedule.length; i++) {
			if (schedule[i].activity == null)
				continue;
			System.out.println("\nHour: " + schedule[i].getHour());
			System.out.println("Activity: " + schedule[i].getActivity());
			System.out.println("Note: " + schedule[i].getNote());
			displayed = true;
		}
		if (!displayed) {
			System.out.println("\nNothing To Show.");
		}
		System.out.println("\n=============================");
	}

	private static void reserveHour(int hour) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("Hour: " + schedule[hour].getHour());
			System.out.print("What is the Activity?\n> ");
			schedule[hour].setActivity(br.readLine());
			System.out.print("Additional Information?\n> ");
			schedule[hour].setNote(br.readLine());
			System.out
					.println("\n" + schedule[hour].getActivity() + " successfully set for " + schedule[hour].getHour());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void addToSchedule() {
		int hour;
		while (true) {
			System.out.println("Which Hour Would You Like to Reserve?");
			hour = getInput();

			if (0 <= hour && hour < schedule.length) {
				if (schedule[hour].getActivity() != null) {
					System.out.println(schedule[hour].getHour() + " is already reserved.");
				} else {
					reserveHour(hour);
				}
				break;
			}

			System.out.println("Invalid Hour. Please Try Again");

		}

	}

	private static void editSchedule() {
		int hour;
		while (true) {
			System.out.println("Which Schedule Hour Would You Like To EDIT?");
			hour = getInput();

			if (0 <= hour && hour < schedule.length) {
				if (schedule[hour].getActivity() == null) {
					System.out.println(schedule[hour].getHour() + " is not scheduled.");
				} else {
					reserveHour(hour);
				}
				break;
			}

			System.out.println("Invalid Hour. Please Try Again");

		}

	}

	private static void deleteFromSchedule() {
		int hour;
		while (true) {
			System.out.println("Which Scheduled Hour Would You Like To DELETE?");
			hour = getInput();

			if (0 <= hour && hour < schedule.length) {
				if (schedule[hour].getActivity() == null) {
					System.out.println(schedule[hour].getHour() + " is already available.");
				} else {
					schedule[hour].setActivity(null);
					schedule[hour].setNote(null);
					System.out.println(schedule[hour].getHour() + " is now available.");
				}
				break;
			}

			System.out.println("Invalid Hour. Please Try Again");

		}

	}

	private static int getInput() {
		int num = -1;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			num = Integer.parseInt(br.readLine());
		} catch (NumberFormatException | IOException e) {
		}
		System.out.println();
		return num;
	}

	private static void startMenu() {
		int input;
		boolean run = true;

		while (run) {
			System.out.println("\nWhat Would You Like To Do?");
			System.out.print(
					"1. View Schedule\n2. Edit Schedule\n3. Add to Schedule\n4. Delete Schedule Activity\n9. Exit\n> ");
			input = getInput();

			switch (input) {
			case 1:
				displaySchedule();
				break;
			case 2:
				editSchedule();
				break;
			case 3:
				addToSchedule();
				break;
			case 4:
				deleteFromSchedule();
				break;
			case 9:
				System.out.println("Closing Scheduler.");
				run = false;
				break;
			default:
				System.out.println("Invalid Input. Please Try again.");
			}
		}

	}

	public static void main(String[] args) {

		schedule = new ScheduledHour[24];

		for (int i = 0; i < schedule.length; i++) {
			schedule[i] = new ScheduledHour(i);
		}

		startMenu();

	}

}
