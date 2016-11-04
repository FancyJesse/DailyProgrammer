package challenge24.intermediate;

public class Main {

	private static final String PI = "3.141592";

	public static void main(String[] args) {
		int limit = 100000;

		double denom, numer;
		for (denom = 1; denom < limit; denom++) {
			numer = denom * 3;
			while (numer / denom < 3.16) {
				if (String.valueOf(numer / denom).startsWith(PI))
					System.out.println((int) numer + "/" + (int) denom);
				numer++;
			}
		}

	}

}
