import java.util.Arrays;
import java.util.*;

public class Java {

	public static void mainm(String[] args) {
		// TODO Auto-generated method stub

		// System.out.println("......."+test());

		// int arr[] = {10, 34, 5, 10, 3, 5, 10};
		// printFreq(arr);

		// lambda expression to implement above
		// functional interface. This interface
		// by default implements abstractFun()
		FuncInterface fobj = (int x) -> System.out.println(2 * x);

		// This calls above lambda expression and prints 10.
		// fobj.abstractFun(5);
		sumDifference();
	}

	// A sample functional interface (An interface with
	// single abstract method
	interface FuncInterface {
		// An abstract function
		void abstractFun(int x);

		// A non-abstract (or default) function
		default void normalFun() {
			System.out.println("Hello");
		}
	}
	// Lambda Expressions: There is a class Country that has methods getContinent()
	// and
	// getPopulation(). Write a function int getPopulation(List<Country> countries,
	// String continent) that computes the total population of a given continent,
	// given a list of all
	// countries and the name of a continent.

	// Java, does the finally block get executed if we insert a return statement
	// inside the try block of a try-catch-finally?

	static String test() {
		String a = null;
		try {
			System.out.println("try");
			// a.chars();
			// return "try";
			// System.out.println("try over");
		} catch (Exception e) {
			System.out.println("catch");
		} finally {
			System.out.println("fianlly");
		}
		System.out.println("over");
		return "method";
	}

	protected void finalize() {

	}

	static void printFreq(int arr[]) {
		// Creates an empty TreeMap
		TreeMap<Integer, Integer> tmap = new TreeMap<Integer, Integer>();

		// Traverse through the given array
		for (int i = 0; i < arr.length; i++) {
			Integer c = tmap.get(arr[i]);

			// If this is first occurrence of element
			if (tmap.get(arr[i]) == null)
				tmap.put(arr[i], 1);

			// If elements already exists in hash map
			else
				tmap.put(arr[i], ++c);
		}

		// Print result
		for (Map.Entry m : tmap.entrySet())
			System.out.println("Frequency of " + m.getKey() + " is " + m.getValue());
	}

	// / to implement a user defined functional interface.

	public static void main(String[] args) {
		int hval[] = { 6, 7, 1, 3, 8, 2, 4 };
		int n = hval.length;
		// System.out.println("Maximum loot value : " + maxLootk(hval, n));
		moveZero();
	}

	static int maxLootk(int hval[], int n) {
		if (n == 0)
			return 0;
		if (n == 1)
			return hval[0];
		if (n == 2)
			return Math.max(hval[0], hval[1]);

		// dp[i] represent the maximum value stolen
		// so far after reaching house i.
		int[] dp = new int[n];

		dp[0] = hval[0];
		dp[1] = hval[1];
		for (int i = 2; i < n; i++)
			dp[i] = Math.max((hval[i] + dp[i - 2]), (dp[i - 1]));

		System.out.println(Arrays.toString(dp));

		return dp[n - 1];
	}

	// Smallest Difference: Given two arrays of integers, compute the pair of values
	// (one value in each
	// array) with the smallest (non-negative) difference. Return the difference.

	static void sumDifference() {
		int[] arr1 = new int[] { 1, 3, 15, 11, 2, 233 }, arr2 = new int[] { 23, 127, 235, 19, 8 };

		int min1 = Integer.MIN_VALUE, max1 = Integer.MAX_VALUE, min2 = Integer.MIN_VALUE, max2 = Integer.MAX_VALUE;
		for (int i : arr1) {
			min1 = i > min1 ? min1 : i;
			max1 = i < max1 ? max1 : i;
		}

		for (int i : arr2) {
			min2 = i > min2 ? min2 : i;
			max2 = i < max2 ? max2 : i;
		}

		int[] merge = new int[arr1.length + arr2.length];
		int[] index = new int[merge.length];
		int i = 0, j = 0, k = 0;

		Arrays.sort(arr1);
		Arrays.sort(arr2);

		System.out.println(Arrays.toString(arr1));
		System.out.println(Arrays.toString(arr2));

		while (k < merge.length) {
			if (i < arr1.length && arr1[i] < arr2[j]) {
				merge[k] = arr1[i];
				i++;
				index[k] = 1;
			} else {
				merge[k] = arr2[j];
				j++;
				index[k] = 2;
			}
			k++;
		}
		System.out.println(Arrays.toString(merge));

		i = 0;
		j = 0;
		int currentDiff = Integer.MAX_VALUE;

		while (i < arr1.length && j < arr2.length) {

			int d = Math.abs(arr1[i] - arr2[j]);

			if (currentDiff > d) {
				System.out.println(" diff " + d + " .." + arr1[i] + "  " + arr2[j]);
				currentDiff = d;
			}

			if (i < arr1.length - 1 && arr1[i] < arr2[j])
				i++;
			else
				j++;

		}

	}

	// English Int: Given any integer, print an English phrase
	// that describes the integer (e.g., "One Thousand,
	// Two Hundred Thirty Four").

	// Sum Swap: Given two arrays of integers,
	// find a pair of values (one value from each array) that you
	// can swap to give the two arrays the same sum.

	static void printInt() {
		int arr1[] = new int[] { 1, 2, 4 }, arr2[] = new int[] { 4, 5, 6 };

		Arrays.sort(arr1);
		Arrays.sort(arr2);

		System.out.println(Arrays.toString(arr1));
		System.out.println(Arrays.toString(arr2));

		int sum1 = 0, sum2 = 0;

		for (int i : arr1)
			sum1 += i;

		for (int i : arr2)
			sum2 += i;

		if ((sum1 + sum2) % 2 != 0) {
			System.out.println("error returning");
			return;
		}

		if (sum1 == sum2) {
			System.out.println("already sorted");
			return;
		}

		int i, j;
		i = j = 0;
		final int bal = sum1 - (sum1 + sum2) / 2;

		while (i < arr1.length && j < arr2.length) {

			int d = Math.abs(arr1[i] - arr2[j]);

			if (arr1[i] - arr2[j] == bal) {
				System.out.println(" diff " + d + " .." + arr1[i] + " " + arr2[j]);
			}

			if (i < arr1.length - 1 && moveFirstPointer(sum1, sum2, arr1[i], arr2[j]))
				i++;
			else
				j++;

		}
	}

	static boolean moveFirstPointer(int sum1, int sum2, int c1, int c2) {
		if (c1 - c2 < sum1 - sum2) {
			return true;
		}
		return false;
	}

	static void printHashing() {
		int arr1[] = new int[] { 4, 1, 2, 1, 1, 2 }, arr2[] = new int[] { 3, 3, 3, 6 };

		HashSet<Integer> set2 = new HashSet<>();

		System.out.println(Arrays.toString(arr1));
		System.out.println(Arrays.toString(arr2));

		int sum1 = 0, sum2 = 0;

		for (int i : arr1) {
			sum1 += i;
		}

		for (int i : arr2) {
			sum2 += i;
			set2.add(i);
		}

		int diff = (sum1 - sum2) / 2;

		for (int i : arr1) {
			if (set2.contains(i - diff)) {
				System.out.println(" diff " + diff + " .." + i + " " + (i - diff));
			}
		}
	}

	// Rank from Stream: Imagine you are reading in a stream of integers.
	// Periodically, you wish to be able
	// to look up the rank of a number x (the number of values less than or equal to
	// x). lmplement the data
	// structures and algorithms to support these operations. That is, implement the
	// method track ( int
	// x), which is called when each number is generated, and the method
	// getRankOfNumber(int
	// x), which returns the number of values less than or equal to x (not including
	// x itself).

	static void moveZero() {
		int arr[] = new int[] { 7, 88, 9, 0, 0, 6, 7, 87, 0 };

		for (int i : arr) {

		}

		System.out.println(Arrays.toString(arr));

	}
	int maxDiff(int arr[], int arr_size) 
	{
		int max_diff = arr[1] - arr[0];
		int min_element = arr[0];
		int i;
		for (i = 1; i < arr_size; i++) 
		{
			if (arr[i] - min_element > max_diff)
				max_diff = arr[i] - min_element;
			if (arr[i] < min_element)
				min_element = arr[i];
		}
		return max_diff;
	}


}
