import java.util.*;

public class FCA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FCA f = new FCA();
		ArrayList<String> list = new ArrayList<String>();
		// f.buildSubSet("asdf", new ArrayList<String>());
		list = f.buildSubSet("asdf");
		String b = "\n\n\n";
		for (String s : list) {
			b += s;
			b += " ";
		}
		System.out.println(b);
//		f.multiply(11, 3);
		f.selectionSort(2);

	}

	// 8.4 Power Set: Write a method to return all subsets of a set.

	private ArrayList<String> buildSubSet(String s, ArrayList<String> list) {
		ArrayList<String> l = new ArrayList<>();

		if (s.length() == 0) {
			l.add("");
			list.addAll(l);
			return list;
		} else if (s.length() == 1) {
			l.add(s);
			list.addAll(l);
			return list;
		} else {
			buildSubSet(s.substring(0, s.length() - 1), list);
			for (String existing : list) {
				l.add(existing + s.charAt(s.length() - 1));
			}
			list.addAll(l);
			return list;
		}
	}

	private ArrayList<String> buildSubSet(String s) {
		ArrayList<String> list = new ArrayList<>();
		list.add("");
		for (int i = 0; i < s.length(); i++) {
			ArrayList<String> newList = new ArrayList<>();
			for (String l : list) {
				newList.add(l + s.charAt(i));
			}
			list.addAll(newList);
		}
		return list;

	}

	private int multiply(int r, int c) {
		int arr[][] = new int[r][c];
		printArray(arr);

		int runningArr[][] = arr;
		int sum = 0;

		while (Carry.canChopFurther(arr)) {
			Carry carry = new Carry(runningArr);
			runningArr = carry.chopped;
			sum += carry.carrySum;
		}
		System.out.println("multiply " + sum);

		return sum;
	}

	public static class Carry {
		int carrySum;;
		int[][] incoming;
		int[][] chopped;

		static boolean canChopFurther(int arr[][]) {
			int r = arr.length;
			int c = arr[0].length;
			return r > 1 && c > 1;
		}

		Carry(int[][] incoming) {
			this.incoming = incoming;
			int r = incoming.length;
			int c = incoming[0].length;
			int remainingSum = 0;

			if (r % 2 != 0) {
				r = r - 1;
				remainingSum += c;
			}

			if (c % 2 != 0) {
				c = c - 1;
				remainingSum += r;
			}

			int r_chopped = 0, c_chopped = 0;
			if (r > 1) {
				r_chopped = r / 2;
				r = r / 2;
			}

			if (c > 2) {
				c_chopped = c / 2;
				c = c / 2;
			}

			if (r_chopped == 0)
				remainingSum += c_chopped;

			if (c_chopped == 0)
				remainingSum += r_chopped;

			if (r_chopped > 0 && c_chopped > 0) {
				int localSum = 0;
				for (int i = 0; i < r_chopped; i++) {
					localSum += c_chopped;
				}

				remainingSum += localSum;
				remainingSum += localSum;
				remainingSum += localSum;
			}
			chopped = new int[r][c];
		}
	}

	// giving an even one here
	private int sumMatrix(int[][] mat) {

		return -1;

	}

	public  int selectionSort(int n) {
		int[] arr = new int[] { 2, 3, 4, 8, 56, 19 };
		for (int i = 0; i < arr.length; i++) {
			int minIndex = i;
			for (int j = i; j < arr.length; j++) {
				 if(arr[minIndex] > arr[j]) {
					 minIndex=j;
				 }
			}
				 int temp = arr[i];
				 arr[minIndex] = arr[i];
				 arr[i]=temp;

			
		}
		System.out.println(Arrays.toString(arr));

		return 1;
	}

	void printArray(int[][] arr) {
		System.out.println(arr.length);
		System.out.println(Arrays.deepToString(arr).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));

	}

	void getAllPermutations() {

	}
}
