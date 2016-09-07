package challenge17.intermediate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Main {

	private static void urlReader(String urlLink, String phrase) {
		try {
			URL url = new URL(urlLink);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

			String line;
			while ((line = in.readLine()) != null)
				if (line.contains(phrase))
					System.out.println(line);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String link = "https://www.fancyjesse.com/";
		String phrase = "Jesse";
		urlReader(link, phrase);
	}

}