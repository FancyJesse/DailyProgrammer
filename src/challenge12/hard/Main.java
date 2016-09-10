package challenge12.hard;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

@SuppressWarnings("restriction")
public class Main {

	private static String getInput(String prompt) {
		while (true) {
			try {
				System.out.print(prompt + "\n> ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				return br.readLine().toUpperCase();
			} catch (IOException e) {
				System.out.println("Something Went Wrong.");
				return "";
			}
		}
	}

	private static void playSound(String path) {
		String rootPath = "./src/challenge12/hard/";
		System.out.printf(path.substring(path.indexOf("/") + 1, path.indexOf(".")) + "..");
		try {
			InputStream in = new FileInputStream(rootPath + path);
			AudioStream audioStream = new AudioStream(in);
			AudioPlayer.player.start(audioStream);
			Thread.sleep(700);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void playNotes(String notes) {
		System.out.println("Playing ...");
		for (int i = 0; i < notes.length(); i++) {
			switch (notes.charAt(i)) {
			case 'A':
				playSound("piano/A.wav");
				break;
			case 'B':
				playSound("piano/B.wav");
				break;
			case 'C':
				playSound("piano/C.wav");
				break;
			case 'D':
				playSound("piano/D.wav");
				break;
			case 'E':
				playSound("piano/E.wav");
				break;
			case 'F':
				playSound("piano/F.wav");
				break;
			case 'G':
				playSound("piano/G.wav");
				break;
			}
		}
		System.out.println("\nDone.");
	}

	public static void main(String[] args) {
		String notes;

		notes = getInput("Enter Notes to Play [A, B, C, D, E, F , G]:");
		playNotes(notes);
	}

}