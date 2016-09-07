package challenge16.easy;

public class Main {

	private static String removeMatches(String str1, String str2) {
		for (int i = 0; i < str2.length(); i++) {
			str1 = str1.replaceAll(String.valueOf(str2.charAt(i)), "");
		}
		return str1;
	}

	public static void main(String[] args) {
		String str1, str2, output;
		str1 = "Daily Programmer";
		str2 = "aeiou ";
		output = removeMatches(str1, str2);
		System.out.println(output);
	}

}
