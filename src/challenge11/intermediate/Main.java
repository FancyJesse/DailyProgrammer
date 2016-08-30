package challenge11.intermediate;

public class Main {

	private static boolean isUpsideDownNumber(int number) {
		char[] digits = new StringBuilder(Integer.toString(number)).reverse().toString().toCharArray();

		for (int i = 0; i < digits.length; i++) {
			switch (digits[i]) {
			case '0':
				digits[i] = '0';
				break;
			case '1':
				digits[i] = '1';
				break;
			case '2':
				digits[i] = '5';
				break;
			case '5':
				digits[i] = '2';
				break;
			case '6':
				digits[i] = '9';
				break;
			case '8':
				digits[i] = '8';
				break;
			case '9':
				digits[i] = '6';
				break;
			default:
				return false;
			}
		}

		return number == Integer.parseInt(new String(digits));
	}

	public static void main(String[] args) {
		int number = 0;
		while (true) {
			if (isUpsideDownNumber(number)) {
				System.out.println(number);
				if (number > 1961)
					break;
			}
			number++;
		}
	}

}
