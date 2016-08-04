package challenge8.easy;

public class Main {

	public static void singIt(int bottlesOfBeer) {
		if (bottlesOfBeer == 0) {
			System.out.println("\nNo more bottles of beer on the wall, no more bottles of beer.");
			System.out.println("Go to the store and buy some more, 99 bottles of beer on the wall.");
			return;
		}

		String s;
		s = bottlesOfBeer == 1 ? "bottle" : "bottles";
		System.out.println(
				"\n" + bottlesOfBeer + " " + s + " of beer on the wall, " + bottlesOfBeer + " " + s + " of beer.");
		bottlesOfBeer -= 1;

		if (bottlesOfBeer == 0) {
			System.out.println("Take one down and pass it around, no more bottles of beer on the wall.");
		} else {
			s = bottlesOfBeer == 1 ? "bottle" : "bottles";
			System.out
					.println("Take one down and pass it around, " + bottlesOfBeer + " " + s + " of beer on the wall.");
		}
		singIt(bottlesOfBeer);
	}

	public static void main(String[] args) {
		singIt(99);
	}

}
