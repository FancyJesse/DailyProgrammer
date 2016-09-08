package challenge14.hard;

//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/* 
 * INCOMPLETE
 * 
 * Multi-Threading not necessary, completes almost instantly with only 1M
 * elements. Possible solution could be to split list into different list
 * (by thread) and implement merge sort between all lists and slowly
 * combine. Still impractical.
 * 
 */

public class Main {

	// private static int getInput(String prompt) {
	// while (true) {
	// try {
	// System.out.print(prompt + "\n> ");
	// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	// return Integer.parseInt(br.readLine());
	// } catch (IOException e) {
	// System.out.println("That is not a Number. Try again.");
	// }
	// }
	// }

	public static void main(String[] args) {
		Random rdm = new Random();
		List<Integer> intList = new ArrayList<>();
		;
		int listSize = 1000000;

		// int threadCount;
		// threadCount = getInput("How Many Threads: ");

		for (int i = 0; i < listSize; i++) {
			intList.add(rdm.nextInt());
		}

		Collections.sort(intList);

		int newLine = 0;
		for (int i : intList) {
			System.out.print(i + " ");
			if (++newLine == 20) {
				System.out.println();
				newLine = 0;
			}
		}

	}

}