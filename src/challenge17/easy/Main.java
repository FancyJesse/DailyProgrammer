package challenge17.easy;

public class Main {

	private static void triangle(int height) {
		int x = 1;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < x; j++)
				System.out.print("@");
			x *= 2;
			System.out.println();
		}
	}

	public static void main(String[] args) {
		triangle(5);
	}

}
