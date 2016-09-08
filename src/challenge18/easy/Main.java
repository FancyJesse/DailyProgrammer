package challenge18.easy;

public class Main {

	private static void displayNumber(String phoneNumber) {
		phoneNumber = phoneNumber.toUpperCase().replaceAll("[^A-Z0-9]", "");
		if (phoneNumber.length() != 11) {
			System.out.println("Invalid Phone Number!");
			return;
		}
		phoneNumber = phoneNumber.replaceAll("[ABC]", "2");
		phoneNumber = phoneNumber.replaceAll("[DEF]", "3");
		phoneNumber = phoneNumber.replaceAll("[GHI]", "4");
		phoneNumber = phoneNumber.replaceAll("[JKL]", "5");
		phoneNumber = phoneNumber.replaceAll("[MNO]", "6");
		phoneNumber = phoneNumber.replaceAll("[PQRS]", "7");
		phoneNumber = phoneNumber.replaceAll("[TUV]", "8");
		phoneNumber = phoneNumber.replaceAll("[XYZ]", "9");

		System.out.println(phoneNumber.charAt(0) + "-" + phoneNumber.substring(1, 4) + "-" + phoneNumber.substring(4, 7)
				+ "-" + phoneNumber.substring(7));
	}

	public static void main(String[] args) {
		String phoneNumber = "1800iREDDIT";
		displayNumber(phoneNumber);
	}

}
