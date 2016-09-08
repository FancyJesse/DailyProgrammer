package challenge18.intermediate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

public class Main {
	private static final String inputFile = "./src/challenge18/intermediate/input.txt";
	private static final String outputFile = "./src/challenge18/intermediate/output.html";
	private static ArrayList<String> allLines;
	private static ArrayList<String> htmlLines;

	private static boolean readFile() {
		allLines = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			String line;
			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (line.isEmpty())
					continue;
				allLines.add(line);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private static boolean parseData() {
		htmlLines = new ArrayList<>();

		for (String line : allLines) {
			if (!line.endsWith(":")) {
				System.out.println("Invalid Document Format: " + line);
				return false;
			}
			String prompt;
			ArrayList<String> constraints = new ArrayList<>();
			try {
				prompt = line.substring(0, line.indexOf(" "));
			} catch (StringIndexOutOfBoundsException e) {
				prompt = line.substring(0, line.indexOf(":"));
			}
			if (line.contains("(")) {
				line = line.substring(line.indexOf("(") + 1, line.lastIndexOf(")"));
				for (String s : line.split(",")) {
					constraints.add(s.replaceAll("[\\[\\]]", "").trim());
				}
			}

			htmlLines.add(prompt);

			if (constraints.size() == 0) {
				htmlLines.add("<input type=\"text\" name=\"" + prompt.toLowerCase() + "\"/>");
			} else if (constraints.size() < 5) {
				for (String c : constraints)
					htmlLines.add("<input type=\"radio\" name=\"" + prompt.toLowerCase() + "\" value=\""
							+ c.toLowerCase().charAt(0) + "\"/> " + c);
			} else {
				htmlLines.add("<select name=\"" + prompt.toLowerCase() + "\">");
				for (String c : constraints)
					htmlLines.add("<option value=\"" + c.toLowerCase().charAt(0) + "\">" + c + "</option>");
				htmlLines.add("</select>");
			}

			htmlLines.add("<br/>");
		}

		return true;
	}

	private static void toFile() {
		try {
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), "UTF-8"));

			String header = "<html><body><form>\n";
			String footer = "<input type=\"submit\" value=\"Submit\"\n</form></body></html>";

			out.write(header);
			for (String s : htmlLines)
				out.write(s + "\n");
			out.write(footer);

			out.close();
			System.out.println("Success.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		if (readFile())
			if (parseData())
				toFile();
	}

}