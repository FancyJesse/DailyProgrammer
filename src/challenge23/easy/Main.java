package challenge23.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

	private static List<List<Integer>> splitList(List<Integer> list) {
		List<Integer> list2 = new ArrayList<>();

		int halfSize = list.size() / 2;
		for (int i = 0; i < halfSize; i++) {
			list2.add(list.get(0));
			list.remove(0);
		}

		List<List<Integer>> twoLists = new ArrayList<>();
		twoLists.add(list2);
		twoLists.add(list);

		return twoLists;
	}

	private static List<Integer> getRandomList() {
		List<Integer> rdmList = new ArrayList<>();

		Random rdm = new Random();
		int listSize = rdm.nextInt(50) + 1;
		for (int i = 0; i < listSize; i++)
			rdmList.add(rdm.nextInt(100));

		return rdmList;
	}

	public static void main(String[] args) {
		List<Integer> rdmList;

		rdmList = getRandomList();
		System.out.println("Original List: " + rdmList.toString());

		int i = 1;
		for (List<Integer> list : splitList(rdmList))
			System.out.println("List " + i++ + ": " + list.toString());

	}

}
