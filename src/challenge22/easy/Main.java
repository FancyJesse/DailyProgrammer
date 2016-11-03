package challenge22.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	private static void appendLists(List<String> list1, List<String> list2) {

		System.out.println("List1: " + list1.toString());
		System.out.println("List2: " + list2.toString());

		System.out.println("Comparing Lists ...");
		for (String item : list1) {
			if (!list2.contains(item)) {
				list2.add(item);
				System.out.println("Added to List2: " + item);
			}
		}

		System.out.println("List2: " + list2.toString());
	}

	public static void main(String[] args) {
		List<String> list1 = new ArrayList<>(Arrays.asList("A", "B", "C", "Extra Item 1", "Extra Item 2", "2"));
		List<String> list2 = new ArrayList<>(Arrays.asList("A", "B", "C", "1", "2", "3"));

		appendLists(list1, list2);
	}

}
