package challenge7.hard;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/*
 * INCOMPLETE
 * 
 * Not working.
 * 
 */
public class Main {
	private static String REDDIT_URL = "https://ssl.reddit.com/api/login";

	private HttpURLConnection getConnection(String url) {
		URL u = null;
		try {
			u = new URL(url);
		} catch (MalformedURLException e) {
			System.out.println("Invalid URL: " + url);
			return null;
		}
		HttpURLConnection connection = null;
		try {
			connection = (HttpURLConnection) u.openConnection();
		} catch (IOException e) {
			System.out.println("Unable to connect: " + url);
			return null;
		}
		connection.setReadTimeout(30000);
		connection.setDoOutput(true);
		return connection;
	}

	private boolean writeToConnection(HttpURLConnection con, String data) {
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(con.getOutputStream()));
			pw.write(data);
			pw.close();
			return true;
		} catch (IOException e) {
			System.out.println("Unable to write: " + e);
			return false;
		}
	}

	private boolean login(String username, String password) {
		HttpURLConnection connection = getConnection(REDDIT_URL);

		if (connection == null)
			return false;

		String data = "user=" + username + "&passwd=" + password;

		if (!writeToConnection(connection, data))
			return false;

		String cookie = connection.getHeaderField("set-cookie");

		if (cookie == null)
			return false;

		System.out.println("ORIGINAL COOKIE: " + cookie);

		cookie = cookie.split(";")[0];
		if (cookie.startsWith("reddit_first")) {
			System.out.println("Error: Unable to login.");
			return false;
		} else if (cookie.startsWith("reddit_session")) {
			System.out.println("Success: " + cookie);
			return true;
		} else {
			System.out.println("Cookie malformed?");
			System.out.println(cookie);
		}
		return false;
	}

	public static void main(String[] args) {
		String username = "";
		String password = "";
		Main main = new Main();
		boolean success;

		success = main.login(username, password);

		System.out.println("LOGIN: " + success);

	}

}
