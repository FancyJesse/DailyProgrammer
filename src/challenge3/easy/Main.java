package challenge3.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private final static char[] Alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
			'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	private final static int superSecretKey = 14;

	private static String encrypt(String rawString) {
		char[] encryptedCharArray;

		encryptedCharArray = rawString.toCharArray();

		int index;
		for (int i = 0; i < encryptedCharArray.length; i++) {
			index = getIndexOf(encryptedCharArray[i]);
			if (index == -1)
				continue;
			encryptedCharArray[i] = Alphabet[(index + superSecretKey) % Alphabet.length];

		}

		return new String(encryptedCharArray);
	}

	private static String decrypt(String encryptedString) {
		char[] decryptedCharArray;

		decryptedCharArray = encryptedString.toCharArray();

		int index;
		for (int i = 0; i < decryptedCharArray.length; i++) {
			index = getIndexOf(decryptedCharArray[i]);
			if (index == -1)
				continue;

			if ((index - superSecretKey) < 0) {
				decryptedCharArray[i] = Alphabet[Alphabet.length + (index - superSecretKey)];

			} else {
				decryptedCharArray[i] = Alphabet[(index - superSecretKey)];
			}

		}

		return new String(decryptedCharArray);
	}

	private static int getIndexOf(char ch) {
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

		encrypted = encrypt(input);
		System.out.println("Encrypted String: " + encrypted);

		decrypted = decrypt(encrypted);
		System.out.println("Decrypted String: " + decrypted);

		System.out.println("Values Match: " + input.equals(decrypted));

	}

}
