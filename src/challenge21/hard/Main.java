package challenge21.hard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	private static Set<String> inputSmartPersonSet(String prompt) {
		Set<String> set = new TreeSet<>();

		System.out.println(prompt);
		while (true) {
			try {
				System.out.print("\n> ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String input = br.readLine().trim();
				if (input.matches("\\w+ : \\w+")) {
					set.add(input);
					System.out.println("Added: " + input);
				} else if (input.equals("q")) {
					System.out.println("Returning: " + set.toString() + "\n");
					return set;
				} else {
					System.out.println("Incorrect Formatting. Try Again.");
				}
			} catch (Exception e) {
				System.out.println("Something Went Wrong. Try Again.");
			}
		}
	}

	private static void organizeBySmartness(Set<String> personSetStr) {
		List<String> smartPersonList = new ArrayList<>();

		for (String personSet : personSetStr) {
			String[] person = personSet.split(" : ");

			if (person[0].equals(person[1])) {
				System.out.println("Ignoring Duplicate Case: " + personSet);
				continue;
			}

			// neither are contained, simply add
			if (!(smartPersonList.contains(person[0]) || smartPersonList.contains(person[1]))) {
				smartPersonList.add(person[0]);
				smartPersonList.add(person[1]);
			}
			// contains at least 1 person
			else {
				// contains dumber person, add smarter person in front
				if (smartPersonList.contains(person[1])) {
					smartPersonList.add(smartPersonList.indexOf(person[1]), person[0]);
				}
				// contains smart person, add dumber person behind
				else {
					smartPersonList.add(smartPersonList.indexOf(person[0]) + 1, person[1]);
				}
			}

		}

		System.out.println("\nSMART LIST");
		for (int i = 0; i < smartPersonList.size(); i++)
			System.out.println(smartPersonList.get(i));

	}

	public static void main(String[] args) {

		Set<String> personsSetStr = inputSmartPersonSet(
				"Enter Smart-Person Set -- [Enter 'q' to End]\nFORMAT: [Smarter-Person] : [Dumber-Person]");

		organizeBySmartness(personsSetStr);

	}

}
