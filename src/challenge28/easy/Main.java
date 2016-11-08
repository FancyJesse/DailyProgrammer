package challenge28.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Main {

	public static void main(String[] args) {
		ArrayList<Integer> intList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 2, 4, 6, 3, 2, 10, 32));
		HashSet<Integer> hSet = new HashSet<>(intList);

		for (int i : hSet)
			intList.remove(intList.indexOf(i));

		System.out.println("Duplicates: " + new HashSet<>(intList).toString());
	}

}
