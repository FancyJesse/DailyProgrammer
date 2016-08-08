package challenge9.intermediate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
 * Uses file from challenge5.intermediate
 * 
 */

public class Main {
	private final static String IN_PATH = "./src/challenge5/intermediate/alice30.txt";
	private final static String OUT_PATH = "./src/challenge9/intermediate/alice30_altered.txt";

	private static int replacePhrase(File input, File output, String phrase, String replacement) {
		int count = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(input));
			FileWriter fw = new FileWriter(output);
			String newLine = System.getProperty("line.separator");
			String line;
			while ((line = br.readLine()) != null) {
				if (line.contains(phrase)) {
					line = line.replace(phrase, replacement);
					count++;
				}
				fw.write(line + newLine);
			}
			br.close();
			fw.close();
		} catch (IOException e) {
		}
		return count;
	}

	public static void main(String[] args) {
		String toReplace = "Alice";
		String replaceWith = "TOADS!!";

		File input = new File(IN_PATH);
		File output = new File(OUT_PATH);
		if (input.exists()) {
			int count;
			count = replacePhrase(input, output, toReplace, replaceWith);
			System.out.println("OCCURANCES OF '" + toReplace + "' REPLACED: " + count);
		} else {
			System.out.println("File Does Not Exist: " + input.getPath());
		}
	}

}
