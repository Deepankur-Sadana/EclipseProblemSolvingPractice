import java.util.*;

public class RandomQuestions {

//	Rand7 from Rands: Implement a method rand7() given rand5( ). That is, given a method that
//	generates a random number between O and 4 (inclusive), write a method that generates a random
//	number between O and 6 (inclusive).

	class Result {
		int maxSum;
		int leftBound;
		int rightBound;
		int upBound;
		int lowBound;

		@Override
		public String toString() {
			return "Result [maxSum=" + maxSum + ", leftBound=" + leftBound + ", rightBound=" + rightBound + ", upBound="
					+ upBound + ", lowBound=" + lowBound + "]";
		}

	}

	public Result maxSum(int input[][]) {
		int rows = input.length;
		int cols = input[0].length;
		int temp[] = new int[rows];
		Result result = new Result();
		for (int left = 0; left < cols; left++) {
			for (int i = 0; i < rows; i++) {
				temp[i] = 0;
			}
			for (int right = left; right < cols; right++) {
				for (int i = 0; i < rows; i++) {
					temp[i] += input[i][right];
				}
				KadaneResult kadaneResult = kadane(temp);
				if (kadaneResult.maxSum > result.maxSum) {
					result.maxSum = kadaneResult.maxSum;
					result.leftBound = left;
					result.rightBound = right;
					result.upBound = kadaneResult.start;
					result.lowBound = kadaneResult.end;
				}
			}
		}
		return result;
	}

	class KadaneResult {
		int maxSum;
		int start;
		int end;

		public KadaneResult(int maxSum, int start, int end) {
			this.maxSum = maxSum;
			this.start = start;
			this.end = end;
		}
	}

	private KadaneResult kadane(int arr[]) {
		int max = 0;
		int maxStart = -1;
		int maxEnd = -1;
		int currentStart = 0;
		int maxSoFar = 0;
		for (int i = 0; i < arr.length; i++) {
			maxSoFar += arr[i];
			if (maxSoFar < 0) {
				maxSoFar = 0;
				currentStart = i + 1;
			}
			if (max < maxSoFar) {
				maxStart = currentStart;
				maxEnd = i;
				max = maxSoFar;
			}
		}
		return new KadaneResult(max, maxStart, maxEnd);
	}

	void printRepeating(int arr[], int size) {
		int i;
		System.out.println("The repeating elements are : ");

		for (i = 0; i < size; i++) {
			if (arr[Math.abs(arr[i])] >= 0)
				arr[Math.abs(arr[i])] = -arr[Math.abs(arr[i])];
			else
				System.out.print(Math.abs(arr[i]) + " ");
		}
	}

	// Driver program
	public void duplicate() {
		int arr[] = { 1, 2, 3, 1, 3, 6, 6 };
		int arr_size = arr.length;

		printRepeating(arr, arr_size);
	}

	public void printSpiral() {
		int[][] a = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, };

	}

	void printSpiral(int[] top, int[] right, int[] bottom, int[] left) {

	}

	void printArray(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {

		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomQuestions r = new RandomQuestions();

//		Result s = r.maxSum(new int[][] {
//            {1, 2, 3},
//            {-3, -2 ,-1},
//            {1,2, 3}
//            });
//		System.out.println(s.maxSum);
//		r.Findsubarraywithgivensum();
//		fibonacci(80);
		mem();

	}

	public void Findsubarraywithgivensum() {

		int arr[] = { 15, 2, 4, 8, 9, 5, 10, 23 };
		int n = arr.length;
		int sum = 23;
		subArraySum(arr, n, sum);
	}

	int subArraySum(int arr[], int n, int sum) {
		int curr_sum = arr[0], start = 0, i;

		// Pick a starting point
		for (i = 1; i <= n; i++) {
			// If curr_sum exceeds the sum, then remove the starting elements
			while (curr_sum > sum && start < i - 1) {
				curr_sum = curr_sum - arr[start];
				start++;
			}

			// If curr_sum becomes equal to sum, then return true
			if (curr_sum == sum) {
				int p = i - 1;
				System.out.println("Sum found between indexes " + start + " and " + p);
				return 1;
			}

			// Add this element to curr_sum
			if (i < n)
				curr_sum = curr_sum + arr[i];

		}

		System.out.println("No subarray found");
		return 0;
	}

	public static int fibonacci(int number) {
		if (number == 1 || number == 2) {
			return 1;
		}
		System.out.println(number);
		return fibonacci(number - 1) + fibonacci(number - 2);
	}

	public static void mem() {
		ArrayList<Integer> list = new ArrayList<>();

		for (int i = 0; i < 10000000; i++) {
			System.out.println(i);
			list.add(i);

		}
	}

	public int findCelebrity(int arr[][]) {
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < arr.length; i++)
			stack.push(i);

		while (!stack.isEmpty()) {
			int A = stack.pop();
			if (stack.isEmpty())// found celebrity as no one left to discart
				return A;
			int B = stack.pop();
			int P = probalyCelebrity(A, B, arr);

			if (stack.isEmpty())
				return P;
			else if (P == -1)
				continue;
			else
				stack.push(P);
		}
		throw new IllegalArgumentException("invalid matrix");
	}

	int probalyCelebrity(int A, int B, int[][] arr) {
		if (arr[A][B] == 0 && arr[B][A] == 0)
			return -1;
		else if (arr[A][B] == 0)
			return A;
		else if (arr[B][A] == 0)
			return B;
		else if (arr[A][B] == 1 && arr[B][A] == 1)// both know each other; must be friends
			return -1;

		return -1;// todo
	}

	public static int binarySearch(int[] a, int start, int end, int target) {
		int middle = (start + end) / 2;
		if (end < start) {
			return -1;
		}

		if (target == a[middle]) {
			return middle;
		} else if (target < a[middle]) {
			return binarySearch(a, start, middle - 1, target);
		} else {
			return binarySearch(a, middle + 1, end, target);
		}
	}

	int bs(int l, int r, int n, int[] arr) {
		if (l > r)
			return -1;
		int mid = l + r / 2;
		if (arr[mid] == n)
			return mid;
		else if (arr[mid] > n) {
			return bs(l, mid - 1, n, arr);
		} else {
			return bs(mid + 1, r, n, arr);
		}

	}

	void sort(int l, int r, int[] arr) {
		if (r - l == 1)
			if (arr[l] < arr[r])
				swap(arr, l, r);

		int mid = getMidPoint(l, r);

		sort(l, mid, arr);
		sort(mid + 1, r, arr);
		merge(arr, l, mid, r);
	}

	void merge(int[] arr, int low, int mid, int high) {
		int i = low, j = mid + 1;
		int looper = low;

	}

	int getMidPoint(int l, int r) {
		return l + 2 / 2;
	}

	void merge() {

	}

	void swap(int[] arr, int... index) {
		int temp = arr[index[0]];
		arr[index[0]] = arr[index[1]];
		arr[index[1]] = temp;
	}

	void quickSort(int l, int r, int[] arr) {
		if (l - r == 1)
			if (arr[l] < arr[r])
				swap(arr, l, r);

		int pivot = arr[r];
		int barrier = l;
		for (int i = l; i < r; i++) {
			if (arr[i] <= pivot) {
				swap(arr, barrier, i);
				barrier++;
			}

		}
		mergePivot(r, barrier, arr);
		quickSort(l, barrier, arr);
		quickSort(barrier + 1, r, arr);
	}

	void mergePivot(int pivolIndex, int barrierIndex, int[] arr) {

	}

	

}
