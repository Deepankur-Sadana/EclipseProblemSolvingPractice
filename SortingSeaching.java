import java.util.Arrays;
import java.lang.reflect.Array;
import java.util.*;

public class SortingSeaching {

	// You are given two sorted arrays, A and B, where A has a large enough buffer
	// at the
	// end to hold B. Write a method to merge B into A in sorted order

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SortingSeaching s = new SortingSeaching();
		// s.builArray(10, 5);
		// s.insertParanthesis();
		// s.find("ball");
		s.createMatrix();

	}

	void builArray(int len, int len2) {
		Random rand = new Random();

		int[] arr = new int[100];
		for (int i = 0; i < len; i++) {
			arr[i] = i * 2;
		}

		int[] arr2 = new int[len2];
		for (int i = 0; i < len2; i++) {
			arr2[i] = i * 2 + 2;
		}

		System.out.println(Arrays.toString(arr));
		System.out.println(Arrays.toString(arr2));
		merge(arr, len - 1, arr2);
		System.out.println(Arrays.toString(arr));
		permutationWithDup();

	}

	private void merge(int[] a, int indexA, int[] b) {

		int indexB = b.length - 1;
		int mergedIndex = a.length - 1;

		while (indexA >= 0 && indexB >= 0) {
			if (indexA == 0) {
				a[mergedIndex] = b[indexB];
				--indexB;
			} else if (indexB == 0) {
				a[mergedIndex] = a[indexA];
				--indexA;
			} else {
				if (b[indexB] > a[indexA]) {
					a[mergedIndex] = b[indexB];
					--indexB;
				} else {
					a[mergedIndex] = a[indexA];
					--indexA;
				}
			}
			--mergedIndex;
		}

	}

	// Permutations without Dups: Write a method to compute all permutations of a
	// string of unique
	// characters.

	void permute(String s) {
		ArrayList<String> list = new ArrayList<>();

		String chopped = s.substring(0, 1);
		s = s.substring(1, s.length());
		list.add(chopped);

		permute(s, list);
		System.out.println("s..." + s);
		System.out.println(Arrays.toString(list.toArray()));
		System.out.println("executed ...." + list.size());

	}

	void permute(String remainder, ArrayList<String> list) {

		String chopped = remainder.substring(0, 1);
		remainder = remainder.substring(1, remainder.length());

		ArrayList<String> temp = new ArrayList<>();
		for (String s : list) {
			for (int i = 0; i < s.length(); i++) {
				String left = s.substring(0, i);
				String right = s.substring(i, s.length());
				String build = left + chopped + right;
				temp.add(build);
			}
		}
		list.addAll(temp);
		if (remainder.length() > 0)
			permute(remainder, list);
	}

	void permuteNoDup() {
		ArrayList<String> list = permutation("ASDF");
		System.out.println(Arrays.toString(list.toArray()));

	}

	ArrayList<String> permutation(String s) {

		if (s.length() == 2) {
			ArrayList<String> list = new ArrayList<>();
			String build = "" + s.charAt(0) + s.charAt(1);
			list.add(build);
			build = "" + s.charAt(1) + s.charAt(0);
			list.add(build);
			return list;
		}

		String pre = s.substring(0, s.length() - 1);
		String chopped = s.substring(s.length() - 1, s.length());

		ArrayList<String> list = permutation(pre);
		ArrayList<String> temp = new ArrayList<>();

		for (String str : list) {
			for (int i = 0; i < str.length(); i++) {
				String left = str.substring(0, i);
				String right = str.substring(i, str.length());
				String build = left + chopped + right;
				temp.add(build);
			}
			temp.add(str + chopped);
		}
		return temp;
	}

	void permutationWithDup() {
		HashSet<String> list = permutationWithDup("AACD");
		System.out.println(Arrays.toString(list.toArray()));

	}

	HashSet<String> permutationWithDup(String s) {
		if (s.length() == 2) {
			HashSet<String> list = new HashSet<>();
			String build = "" + s.charAt(0) + s.charAt(1);
			list.add(build);
			build = "" + s.charAt(1) + s.charAt(0);
			list.add(build);
			return list;
		}

		String pre = s.substring(0, s.length() - 1);
		String chopped = s.substring(s.length() - 1, s.length());

		HashSet<String> list = permutationWithDup(pre);
		HashSet<String> temp = new HashSet<>();

		for (String str : list) {
			for (int i = 0; i < str.length(); i++) {
				String left = str.substring(0, i);
				String right = str.substring(i, str.length());
				String build = left + chopped + right;
				temp.add(build);
			}
			temp.add(str + chopped);
		}
		return temp;
	}

	void insertParanthesis() {
		HashSet<String> set = insertParanthesis(3);
		System.out.println(Arrays.toString(set.toArray()));

	}

	String P = "()";

	HashSet<String> insertParanthesis(int count) {
		if (count == 1) {
			HashSet<String> set = new HashSet<String>();
			set.add(P);
			return set;
		}
		HashSet<String> set = insertParanthesis(count - 1);
		HashSet<String> temp = new HashSet<>();
		for (String str : set) {
			for (int i = 0; i < str.length(); i++) {
				String left = str.substring(0, i);
				String right = str.substring(i, str.length());
				String build = left + P + right;
				temp.add(build);
			}
			temp.add(str + P);
		}
		return temp;

	}

	private String encodeString(String s) {
		String encoded = null;

		HashMap<Character, Integer> map = new HashMap<>();

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (map.get(c) == null) {
				map.put(c, 0);
			} else {
				int count = map.get(c);
				map.put(c, ++count);
			}
		}

		Set<Character> set = map.keySet();
		char[] charArray = new char[set.size()];
		int i = 0;
		for (char c : set) {
			charArray[i++] = c;
		}
		Arrays.sort(charArray);

		for (char c : set) {
			String str = String.valueOf(c);
			encoded += str;
			encoded += map.get(c);
		}
		return encoded;
	}

	String[] buildStrings() {
		return new String[] { "asdf", "1234", "abcd", "sadf", "4321", "deep", "anku", "kuna", "peed" };
	}

	void groupAnaGram(String[] strings) {
		Encoded[] e = new Encoded[strings.length];
		int i = 0;
		HashMap<String, ArrayList<String>> map = new HashMap<>();

		for (String s : strings) {
			String en = encodeString(s);
			map.put(en, new ArrayList<>());
			e[i++] = new Encoded(s, encodeString(s));
		}

		List<Encoded> list = Arrays.asList(e);
		// Collections.sort(list, new MyComparator());

		for (Encoded en : list) {
			ArrayList<String> arrayList = map.get(en.encodedString);
			arrayList.add(en.originalString);
		}
		for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
			System.out.println(Arrays.toString(entry.getValue().toArray()));
		}

	}

	public class MyComparator implements Comparator<Encoded> {
		public int compare(Encoded e1, Encoded e2) {
			return e1.encodedString.compareTo(e2.encodedString);
		}
	}

	public class Encoded {
		String encodedString, originalString;

		Encoded(String originalString, String encodedString) {
			this.encodedString = encodedString;
			this.originalString = originalString;

		}

	}

	int[] findRotatedIndex() {
		int[] arr = new int[] { 15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14 };
		System.out.println(findRotatedIndex(arr, 0, arr.length - 1, 0, 0));
		return arr;
	}

	int findRotatedIndex(int[] arr, int l, int r, int firstElement, int lastElement) {
		if (r >= l) {
			int mid = l + (r - l) / 2;

			if (isThisOrigin(arr, mid))
				return mid;

			if (originLiesOnLeft(arr, mid)) {
				return findRotatedIndex(arr, l, mid - 1, firstElement, lastElement);
			} else {
				return findRotatedIndex(arr, mid + 1, r, firstElement, lastElement);
			}
		}
		return 0;
	}

	boolean isThisOrigin(int[] arr, int index) {
		if (index > 0) {
			return arr[index - 1] > arr[index];
		}
		return false;
	}

	boolean originLiesOnLeft(int[] arr, int index) {
		if (index > 0) {
			return arr[index] < arr[0];
		}
		return false;
	}

	private void findIndexOfElement() {
		int[] arr = new int[] { 15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14 };
		int element = 16;

		int rotationIndex = findRotatedIndex(arr, 0, arr.length - 1, 0, 0);

		System.out.println("rotaion " + rotationIndex);
		int result = binarySearch(arr, 0, arr.length - 1, element, rotationIndex);
		System.out.println("result " + getTransFormedIndex(arr.length, result, rotationIndex));

	}

	int getTransFormedIndex(int arrLength, int index, int rotatedIndex) {
		if (index + rotatedIndex > arrLength - 1)
			return (index + rotatedIndex - (arrLength));
		else
			return index + rotatedIndex;
	}

	int binarySearch(int arr[], int l, int r, int x, int rotatedIndex) {

		if (r >= l) {
			int mid = l + (r - l) / 2;

			// If the element is present at the
			// middle itself
			if (arr[getTransFormedIndex(arr.length, mid, rotatedIndex)] == x)
				return mid;

			// If element is smaller than mid, then
			// it can only be present in left subarray
			if (arr[getTransFormedIndex(arr.length, mid, rotatedIndex)] > x)
				return binarySearch(arr, l, mid - 1, x, rotatedIndex);

			// Else the element can only be present
			// in right subarray
			return binarySearch(arr, mid + 1, r, x, rotatedIndex);
		}

		// We reach here when element is not present
		// in array
		return -1;
	}

	// Sparse Search: Given a sorted array of strings that is interspersed with
	// empty strings, write a
	// method to find the location of a given string.

	private void find(String s) {
		String[] arr = new String[] { "", "", "", "car", "", "", "dad", "", "ball" };
		System.out.println(s + " at " + binarySearch(arr, 0, arr.length, s));
	}

	int binarySearch(String arr[], int l, int r, String x) {
		if (r >= l) {
			int mid = l + (r - l) / 2;

			// If the element is present at the
			// middle itself
			if (arr[mid].compareTo(x) == 0) {
				return mid;
			} else if (arr[mid].equals("")) {
				binarySearch(arr, l, mid - 1, x);
				binarySearch(arr, mid + 1, r, x);

			} else if (arr[mid].compareTo(x) > 0) {// search left
				return binarySearch(arr, l, mid - 1, x);
			} else {// search right
				return binarySearch(arr, mid + 1, r, x);
			}
		}

		// We reach here when element is not present
		// in array
		return -1;
	}

	// 10.9 Sorted Matrix Search: Given an M x N matrix in which each row and each
	// column is sorted in
	// ascending order, write a method to find an element.

	private void createMatrix() {
		int marks[][] = { //
				{ 50, 60, 65, 67, 70 },
				//
				{ 62, 65, 70, 75, 81 },
				//
				{ 72, 66, 77, 80, 83 } };
		// System.out.println(Arrays.deepToString(marks));

		for (int[] row : marks) {
			System.out.println(Arrays.toString(row));
		}
		find(marks);
	}

	private void find(int[][] marks) {

		System.out.println(" a "+marks.length +" b "+marks[0].length);
		System.out.println("..." + binarySearch(marks, 0, marks[0].length - 1, 0, marks.length - 1, 62));
	}

	String binarySearch(int arr[][], int l, int r, int t, int b, int x) {
		if (r >= l && b >= t) {
			int mid_C = l + (r - l) / 2;

			int mid_R = t + (b - t) / 2;

			// // If the element is present at the
			// // middle itself
			// if (arr[mid] == x)
			// return mid;
			//
			// // If element is smaller than mid, then
			// // it can only be present in left subarray
			// if (arr[mid] > x)
			// return binarySearch(arr, l, mid-1, x);
			//
			// // Else the element can only be present
			// // in right subarray
			// return binarySearch(arr, mid+1, r, x);

			if (arr[mid_R][mid_C] == x)
				return "..mid_R " + mid_R + " mid_C " + mid_C;

			if (arr[mid_R][mid_C] > x)
				return binarySearch(arr, l, mid_C - 1, t, mid_R - 1, x);
			return binarySearch(arr, mid_C + 1, r, mid_R + 1, b, x);

		}

		// We reach here when element is not present
		// in array
	
		return null;
	}

}
