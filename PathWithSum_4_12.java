import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PathWithSum_4_12 {

	// Paths with Sum: You are given a binary tree in which each node contains an
	// integer value (which
	// might be positive or negative). Design an algorithm to count the number of
	// paths that sum to a
	// given value. The path does not need to start or end at the root or a leaf,
	// but it must go downwards
	// (traveling only from parent nodes to child nodes).
	// Hints:#6, #14, #52, #68, #77, #87, #94, #103, #108, #115

	class Node {
		Node left, right;
		int data;
	}

	// Returns true if arr[] has a subarray with sero sum
	static int subArrayExistsWithGivenSum(int arr[], int k) {
		// Creates an empty hashMap hM
		HashMap<Integer, ArrayList<Integer>> hM = new HashMap<Integer, ArrayList<Integer>>();

		int count = 0;
		// Initialize sum of elements
		int sum = 0;
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(-1);
		hM.put(0, list);

		// Traverse through the given array
		for (int i = 0; i < arr.length; i++) {
			// Add current element to sum
			sum += arr[i];

			ArrayList<Integer> pre = hM.get(sum - k);
			if (pre != null) {
				for (int n : pre) {
					String b = " ";
					b += n+1;
					b += " ";
					System.out.println("existes with " + b + " .. till " + i);
				}
			}

			ArrayList<Integer> l = hM.get(sum);
			if (l != null)
				l.add(i);
			else {
				ArrayList<Integer> l1 = new ArrayList<Integer>();
				l1.add(i);
				hM.put(sum, l1);
			}

		}
		return count;
	}

	// driver code
	public static void main(String arg[]) {
		int arr[] = { 1, 2, 4, -1, 3, 5, 1 };
		int c = subArrayExistsWithGivenSum(arr, 6);
		if (c != 0)
			System.out.println("Found " + c);
		else
			System.out.println("No Such Sub Array Exists!");
	}

	void traverse(Node root, ArrayList<Integer> visited) {
		if (root == null && visited.size() > 0) {
			int[] intArray = new int[visited.size()];
			for (int i = 0; i < intArray.length; i++) {
				intArray[i] = visited.get(i);
			}
			return;
		}

		traverse(root.left, new ArrayList<Integer>(visited));
		traverse(root.right, new ArrayList<Integer>(visited));
	}

}
