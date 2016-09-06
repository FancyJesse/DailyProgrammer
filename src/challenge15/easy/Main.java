package challenge15.easy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/*
 * Thanks to http://www.randomtext.me/ for the random text
 * 
 */

public class Main {
	private final static String FILE_PATH = "./src/challenge15/easy/textFileReg.txt";
	private final static String LJF_PATH = "./src/challenge15/easy/textFileLJ.txt";
	private final static String RJF_PATH = "./src/challenge15/easy/textFileRJ.txt";
	private static ArrayList<String> allLines;

	private static void readFile(File file) {
		allLines = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				allLines.add(line.trim());
			}
			br.close();
		} catch (IOException e) {
		}
	}

	private static void toFile(String filePath, ArrayList<String> lines) {
		try {
			File file = new File(filePath);
			PrintWriter pr = new PrintWriter(file);
			for (String line : lines) {
				pr.println(line);
			}
			pr.close();
			System.out.println(file.getName() + " has been succesfully created.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void fileJustify() {
		int maxCharCnt = 0;
		for (String line : allLines) {
			if (line.toCharArray().length > maxCharCnt) {
				maxCharCnt = line.toCharArray().length;
			}
		}

		ArrayList<String> leftJust = new ArrayList<>();
		ArrayList<String> rightJust = new ArrayList<>();

		for (int i = 0; i < allLines.size(); i++) {
			int diff = maxCharCnt - allLines.get(i).toCharArray().length;
			String spaces = "";
			for (int j = 0; j < diff; j++) {
				spaces += " ";
			}
			leftJust.add(allLines.get(i) + spaces);
			rightJust.add(spaces + allLines.get(i));
		}

		toFile(LJF_PATH, leftJust);
		toFile(RJF_PATH, rightJust);

	}

	public static void main(String[] args) {
		File file = new File(FILE_PATH);
		if (file.exists()) {
			readFile(file);
			fileJustify();
		} else {
			System.out.println("File Does Not Exist: " + file.getPath());
		}
	}

}
