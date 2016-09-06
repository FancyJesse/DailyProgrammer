package challenge14.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		String[] elements = { "a", "b", "c", "1", "2", "3", "00", "zz" };
		int blockSize = 2;
		List<List<String>> shortListList = new ArrayList<>();
		for (int i = 0; i < elements.length; i += blockSize) {
			List<String> shortList = Arrays
					.asList(Arrays.copyOfRange(elements, i, Math.min(i + blockSize, elements.length)));
			Collections.reverse(shortList);
			shortListList.add(shortList);
		}
		System.out.println("Input: " + Arrays.asList(elements).toString());
		System.out.println("Block Size: " + blockSize);
		System.out.println("Output: " + shortListList.toString());
	}

}
