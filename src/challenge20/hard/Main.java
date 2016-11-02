package challenge20.hard;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

public class Main {

	private static final String POPUP_MSG_TITLE = "Challenge #20 - Hard";

	private static void popupMessage(String message, int frequency) {

		System.out.println("Popup Message Alarm Set!");
		System.out.println("Message: " + message);
		System.out.println("Frequency: Every " + frequency + " second(s)");

		Timer timer = new Timer();

		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				JOptionPane.showMessageDialog(null, message, POPUP_MSG_TITLE, JOptionPane.INFORMATION_MESSAGE);
			}
		}, frequency * 1000, frequency * 1000);

	}

	public static void main(String[] args) {

		popupMessage("Stop Procrastinating!", 60 * 60 * 2);
	}

}
