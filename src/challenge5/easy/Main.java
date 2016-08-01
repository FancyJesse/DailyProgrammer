package challenge5.easy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private static final String CREDENTIALS_PATH = "./src/challenge5/easy/credentials.txt";
	private static String username;
	private static String password;

	private static String getInput(String prompt) {

		while (true) {
			try {
				System.out.print(prompt + "\n> ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				return br.readLine().trim();
			} catch (IOException e) {
			}
		}
	}

	private static boolean setUsernamePassword(File file) {

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String[] credentials = br.readLine().split(":");
			username = credentials[0].trim();
			password = credentials[1].trim();
			br.close();
		} catch (Exception e) {
		}

		return username != null && password != null;
	}

	private static boolean login(String username, String password) {
		return Main.username.equals(username) && Main.password.equals(password);
	}

	public static void main(String[] args) {
		File file = new File(CREDENTIALS_PATH);

		if (file.exists()) {
			if (setUsernamePassword(file)) {
				String[] inputCredentials;

				// manual input
				inputCredentials = new String[2];
				inputCredentials[0] = getInput("Username: ");
				inputCredentials[1] = getInput("Password: ");
				System.out.println("Manual Login: " + login(inputCredentials[0], inputCredentials[1]));

			} else {
				System.out.println("Failed To Retrieve Credentials From: " + file.getPath());
			}
		} else {
			System.out.println("Could Not Find: " + file.getPath());
		}
	}

}
