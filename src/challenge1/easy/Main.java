package challenge1.easy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {

	private String getInput(String question) {
		String input = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print(question + "\n> ");

		try {
			input = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return input;
	}

	private void toFile(String name, String age, String username) {
		try {
			File file = new File("./src/challenge1/easy/" + username + ".txt");
			PrintWriter pr = new PrintWriter(file);
			pr.println("Name: " + name);
			pr.println("Age: " + age);
			pr.println("Reddit_Username: " + username);
			pr.close();
			System.out.println(file.getName() + " has been succesfully created.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		Main main = new Main();

		String name, age, username;

		name = main.getInput("What is your name?");
		age = main.getInput("What is your age?");
		username = main.getInput("What is your reddit username?");

		System.out.println("Your name is " + name + ", you are " + age + " years old, and your reddit username is "
				+ username + ".");

		main.toFile(name, age, username);

	}

}
