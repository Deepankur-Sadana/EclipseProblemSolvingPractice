import java.util.*;

public class Utils {

	public static void pint(int[] arr) {
		System.out.println(Arrays.toString(arr));

	}

	public static void pint(int[][] arr) {
		for (int[] x : arr) {
			for (int y : x) {
				System.out.print(y + " ");
			}
			System.out.println();
		}
	}

	public static void print(boolean[][] arr) {
		for (boolean[] x : arr) {
			for (boolean y : x) {
				System.out.print(y ? 1 + " " : 0 + " ");
			}
			System.out.println();
		}

	}

	public static void main(String args[]) {
		quickSort();
	}

	static void quickSort() {
		int[] arr = new int[] {2,3,1,9,5,8,4};
		quickSort(0,arr.length,arr);
		pint(arr);
	}

	static void quickSort(int l, int r, int[] arr) {
		if (r >= l)
			return;

		int pivot = r;
		int i = l;
		int j = r - 1;
		while (i <= j) {
			if (arr[j] < arr[j]) {
				while (arr[j] > arr[pivot]) {
					j--;
				}
				swap(arr, i, j);
			}

		}
		swap(arr, j, pivot);
		
		quickSort(l, pivot - 1, arr);
		quickSort(pivot + 1, r, arr);

	}

	static void swap(int arr[], int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
}
