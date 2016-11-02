package challenge20.easy;

public class Main {

	public static void main(String[] args) {
		int limit = 2000;

		for (int i = 1; i < limit; i++) {

			boolean isPrime = true;
			for (int j = 2; j < i; j++) {
				if (i % j == 0) {
					isPrime = false;
					break;
				}
			}

			if (isPrime)
				System.out.println(i);
		}

	}

}
