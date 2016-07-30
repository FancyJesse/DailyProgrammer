package challenge2.hard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Main {

	private static String getInput(String prompt) {
		String input = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print(prompt + "\n> ");
		try {
			input = br.readLine().toUpperCase();
		} catch (IOException e) {
		}

		return input;
	}

	private static class StopWatch {
		private ArrayList<Lap> laps;

		public void init() {
			this.laps = new ArrayList<>();
			String input;
			boolean running;

			running = true;
			Lap lap = new Lap();
			while (running) {
				input = getInput("S: Start/Stop\nL: New Lap\nR: Reset\nE: Exit");
				switch (input) {
				case "S":
					lap.startStop();
					if (lap.running)
						System.out.println("StopWatch Started");
					else
						System.out.println("StopWatch Stopped");
					break;
				case "L":
					if (!lap.running) {
						System.out.println("You Must Start The StopWatch First");
						break;
					}
					lap.startStop();
					System.out.println("Lap " + (this.laps.size() + 1) + ": " + lap.getTime() + " seconds");
					laps.add(lap);
					lap = new Lap();
					lap.startStop();
					break;
				case "R":
					this.laps = new ArrayList<>();
					lap = new Lap();
					break;
				case "E":
					running = false;
					toFile();
					break;
				default:
					System.out.println("Invalid Input");
				}
			}

		}

		private void toFile() {
			try {
				File file = new File("./src/challenge2/hard/challenge2Hard.txt");
				PrintWriter pr = new PrintWriter(file);
				pr.println("Date Stored: "
						+ new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
				int count = 1;
				pr.println();
				for (Lap lap : laps) {
					if (lap.running)
						continue;
					pr.println("Lap " + count++ + ": " + lap.getTime() + " seconds");
				}
				pr.close();
				System.out.println(file.getName() + " has been succesfully created.");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		private class Lap {
			private long time = 0;
			private boolean running = false;

			public void startStop() {
				time = System.currentTimeMillis() - time;
				running = !running;
			}

			public double getTime() {
				return (time) / 1000.00;
			}

		}

	}

	public static void main(String[] args) {
		StopWatch stopWatch = new StopWatch();

		stopWatch.init();

	}

}
