package challenge23.intermediate;

public class Main {

	private static boolean mcNuggetNum(int num) {
		int m6 = num - 6;
		int m9 = num - 9;
		int m20 = num - 20;

		if (m6 == 0 || m9 == 0 || m20 == 0)
			return true;
		else if (m6 < 0)
			return false;

		return mcNuggetNum(m6) || mcNuggetNum(m9) || mcNuggetNum(m20);
	}

	public static void main(String[] args) {
		int limit = 1000;

		for (int i = 1; i <= limit; i++)
			if (!mcNuggetNum(i))
				System.out.println(i);

	}

}
