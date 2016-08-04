package challenge7.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private static Object[][] morseAlphaPairs = { { 'A', ".-" }, { 'B', "-..." }, { 'C', "-.-." }, { 'D', "-.." },
			{ 'E', "." }, { 'F', "..-." }, { 'G', "--." }, { 'H', "...." }, { 'I', ".." }, { 'J', ".---" },
			{ 'K', "-.-" }, { 'L', ".-.." }, { 'M', "--" }, { 'N', "-." }, { 'O', "---" }, { 'P', ".--." },
			{ 'Q', "--.-" }, { 'R', ".-." }, { 'S', "..." }, { 'T', "-" }, { 'U', "..-" }, { 'V', "...-" },
			{ 'W', ".--" }, { 'X', "-..-" }, { 'Y', "-.--" }, { 'Z', "	--.." }, { ',', "--..--" }, { ':', "---..." },
			{ '\'', ".----." }, { '/', "-..-." }, { '?', ".-..-." }, { '@', ".--.-." }, { '=', "-...-" } };

	private static String getInput(String prompt) {
		while (true) {
			try {
				System.out.print(prompt + "\n> ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				return br.readLine().toUpperCase();
			} catch (IOException e) {
			}
		}
	}

	private static String getAlpha(String morseCode) {
		for (Object[] pair : morseAlphaPairs) {
			if (pair[1].equals(morseCode)) {
				return pair[0].toString();
			}
		}
		return null;
	}

	private static String getMorse(char ch) {
		if (ch == ' ')
			return " ";
		for (Object[] pair : morseAlphaPairs) {
			if ((char) pair[0] == ch) {
				return (String) pair[1];
			}
		}
		return null;
	}

	private static String translator(String message) {
		String translatedMessage = "";
		String buffer;

		if (!message.matches("[A-Z]+")) {
			System.out.println("Found Morse");
			for (String word : message.split("/")) {
				for (String letter : word.split(" ")) {
					buffer = getAlpha(letter);
					if (buffer == null)
						buffer = " ";
					translatedMessage += buffer;
				}
			}
		} else {
			char[] charList = message.toCharArray();
			for (int i = 0; i < charList.length; i++) {
				buffer = getMorse(charList[i]);
				if (buffer == null)
					buffer = "" + charList[i];
				else if (buffer.equals(" "))
					buffer = "/";
				translatedMessage += buffer + " ";
			}
		}

		return translatedMessage;
	}

	public static void main(String[] args) {
		String toTranslate, translatedMessage;

		toTranslate = getInput("Enter your message to translate to morse code or alphabet: ");
		translatedMessage = translator(toTranslate);
		System.out.println("Translated Message:\n" + translatedMessage);

	}

}
