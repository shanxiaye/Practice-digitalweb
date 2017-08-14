package com.digitalweb.util;
import java.util.*;
public class SetTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		List arrayList = new ArrayList(Arrays.asList("6 2 1 5 5 7 7".split(" ")));
//		Collections.sort(arrayList);
//		System.out.println(arrayList);
//		Collections.sort(arrayList,Collections.reverseOrder());
//		System.out.println(arrayList);
		SortedSet treeSet = new TreeSet(Collections.reverseOrder());
		treeSet.addAll(Arrays.asList("6 2 1 5 5 7 7".split(" ")));
		System.out.println(treeSet);

	}

}
