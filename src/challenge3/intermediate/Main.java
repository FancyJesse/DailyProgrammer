package challenge3.intermediate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private final static char[] Alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	private static String encryptAndDecrypt(String rawString) {
		char[] charArray;

		charArray = rawString.toCharArray();

		int index;
		boolean upperCase;
		for (int i = 0; i < charArray.length; i++) {
			upperCase = Character.isUpperCase(charArray[i]);
			index = getIndexOf(charArray[i]);
			if (index == -1)
				continue;

			if (index < Alphabet.length / 2) {
				charArray[i] = Alphabet[(index + Alphabet.length / 2)];
			} else {
				charArray[i] = Alphabet[(index - Alphabet.length / 2)];
			}

			charArray[i] = upperCase ? Character.toLowerCase(charArray[i]) : Character.toUpperCase(charArray[i]);

		}

		return new String(charArray);
	}

	private static int getIndexOf(char ch) {
		ch = Character.toUpperCase(ch);
		for (int i = 0; i < Alphabet.length; i++) {
			if (Alphabet[i] == ch) {
				return i;
			}
		}
		return -1;
	}

	private static String getInput() {
		while (true) {
			try {
				System.out.print("Enter Something to Encrypt\n> ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				return br.readLine();
			} catch (IOException e) {
			}
		}
	}

	public static void main(String[] args) {
		String input, encrypted, decrypted;

		input = getInput();

		encrypted = encryptAndDecrypt(input);
		System.out.println("Encrypted String: " + encrypted);

		decrypted = encryptAndDecrypt(encrypted);
		System.out.println("Decrypted String: " + decrypted);

		System.out.println("Values Match: " + input.equals(decrypted));

	}

}
