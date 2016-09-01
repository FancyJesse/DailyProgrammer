package challenge13.intermediate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {

	private static String getInput(String prompt) {
		while (true) {
			try {
				System.out.print(prompt + "\n> ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				return br.readLine();
			} catch (IOException e) {
				System.out.println("That is not a Number. Try again.");
			}
		}
	}

	private static void toFile(String text) {
		try {
			File file = new File("./src/challenge13/intermediate/reversedText.txt");
			PrintWriter pr = new PrintWriter(file);
			pr.println(text);
			pr.close();
			System.out.println(file.getName() + " has been succesfully created.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String input;
		input = getInput("Input String to Reverse");
		StringBuilder reverse = new StringBuilder(input).reverse();
		toFile(reverse.toString());
	}

}