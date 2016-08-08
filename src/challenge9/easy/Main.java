package challenge9.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

	private static String getInput() {
		while (true) {
			try {
				System.out.print("> ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				return br.readLine();
			} catch (NumberFormatException | IOException e) {
				return "!q";
			}
		}
	}

	public static void main(String[] args) {
		ArrayList<String> arrayList = new ArrayList<>();
		String buffer;

		System.out.println("Enter Your List of Items to Sort. [!q to End List]");
		while (true) {
			buffer = getInput();
			if (buffer.equals("!q"))
				break;
			arrayList.add(buffer);
		}

		Collections.sort(arrayList);
		System.out.println("Sorted List: " + Arrays.toString(arrayList.toArray()));
	}

}
