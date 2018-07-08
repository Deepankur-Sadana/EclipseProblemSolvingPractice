
//Java program to find minimum time required to make all
//oranges rotten

import java.util.*;

public class RotOrange {
	public final static int R = 3;
	public final static int C = 5;

	// structure for storing coordinates of the cell
	static class Ele {
		int x = 0;
		int y = 0;

		Ele(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	// function to check whether a cell is valid / invalid
	static boolean isValid(int i, int j) {
		return (i >= 0 && j >= 0 && i < R && j < C);
	}

	// Function to check whether the cell is delimiter
	// which is (-1, -1)
	static boolean isDelim(Ele temp) {
		return (temp.x == -1 && temp.y == -1);
	}

	// Function to check whether there is still a fresh
	// orange remaining
	static boolean checkAll(int arr[][]) {
		for (int i = 0; i < R; i++)
			for (int j = 0; j < C; j++)
				if (arr[i][j] == 1)
					return true;
		return false;
	}

	// This function finds if it is possible to rot all oranges or not.
	// If possible, then it returns minimum time required to rot all,
	// otherwise returns -1
	static int rotOranges(int arr[][]) {
		// Create a queue of cells
		Queue<Ele> Q = new LinkedList<>();
		Ele temp;
		int ans = 0;
		// Store all the cells having rotten orange in first time frame
		for (int i = 0; i < R; i++)
			for (int j = 0; j < C; j++)
				if (arr[i][j] == 2)
					Q.add(new Ele(i, j));

		// Separate these rotten oranges from the oranges which will rotten
		// due the oranges in first time frame using delimiter which is (-1, -1)
		Q.add(new Ele(-1, -1));

		// Process the grid while there are rotten oranges in the Queue
		while (!Q.isEmpty()) {
			// This flag is used to determine whether even a single fresh
			// orange gets rotten due to rotten oranges in current time
			// frame so we can increase the count of the required time.
			boolean flag = false;

			// Process all the rotten oranges in current time frame.
			while (!isDelim(Q.peek())) {
				temp = Q.peek();

				// Check right adjacent cell that if it can be rotten
				if (isValid(temp.x + 1, temp.y + 1) && arr[temp.x + 1][temp.y] == 1) {
					if (!flag) {
						// if this is the first orange to get rotten, increase
						// count and set the flag.
						ans++;
						flag = true;
					}
					// Make the orange rotten
					arr[temp.x + 1][temp.y] = 2;

					// push the adjacent orange to Queue
					temp.x++;
					Q.add(new Ele(temp.x, temp.y));

					// Move back to current cell
					temp.x--;
				}

				// Check left adjacent cell that if it can be rotten
				if (isValid(temp.x - 1, temp.y) && arr[temp.x - 1][temp.y] == 1) {
					if (!flag) {
						ans++;
						flag = true;
					}
					arr[temp.x - 1][temp.y] = 2;
					temp.x--;
					Q.add(new Ele(temp.x, temp.y)); // push this cell to Queue
					temp.x++;
				}

				// Check top adjacent cell that if it can be rotten
				if (isValid(temp.x, temp.y + 1) && arr[temp.x][temp.y + 1] == 1) {
					if (!flag) {
						ans++;
						flag = true;
					}
					arr[temp.x][temp.y + 1] = 2;
					temp.y++;
					Q.add(new Ele(temp.x, temp.y)); // Push this cell to Queue
					temp.y--;
				}

				// Check bottom adjacent cell if it can be rotten
				if (isValid(temp.x, temp.y - 1) && arr[temp.x][temp.y - 1] == 1) {
					if (!flag) {
						ans++;
						flag = true;
					}
					arr[temp.x][temp.y - 1] = 2;
					temp.y--;
					Q.add(new Ele(temp.x, temp.y)); // push this cell to Queue
				}
				Q.remove();

			}
			// Pop the delimiter
			Q.remove();

			// If oranges were rotten in current frame than separate the
			// rotten oranges using delimiter for the next frame for processing.
			if (!Q.isEmpty()) {
				Q.add(new Ele(-1, -1));
			}

			// If Queue was empty than no rotten oranges left to process so exit
		}

		// Return -1 if all arranges could not rot, otherwise -1.s
		return (checkAll(arr)) ? -1 : ans;

	}

	// Drive program
	public static void mainold(String[] args) {
		int arr[][] = { { 2, 1, 0, 2, 1 }, { 1, 0, 1, 2, 1 }, { 1, 0, 0, 2, 1 } };
		int ans = rotOranges(arr);
		// if (ans == -1)
		// System.out.println("All oranges cannot rot");
		// else
		// System.out.println("Time required for all oranges to rot = " + ans);
		// max();
		new RotOrange().findSubArray();
	}

	// Drive program
	static int main() {
		int price[] = { 10, 22, 5, 75, 65, 80, 10, 100 };
		int n = price.length;
		System.out.println(maxProfit(price, n));

		return 0;
	}

	static int maxProfit(int arr[], int n) {
		int min = arr[0];
		int max = 0;
		boolean flag = false;
		for (int i = 0; i < arr.length - 1; i++) {
			if (flag) {// search maxima
				if (arr[i + 1] > max) {
					max = arr[i + 1];
				} else {// sell here
					flag = false;
					System.out.println(" buy at " + min + " sell " + max);
					min = arr[i + 1];
				}
			} else {// search minima
				if (min > arr[i + 1]) {
					min = arr[i + 1];
				} else {
					max = arr[i + 1];
					flag = true;
				}
			}
		}
		if (flag) {
			System.out.println(" buy at " + min + " sell " + max);
		}
		return -1;
	}

	// Letters and Numbers: Given an array filled with letters and numbers, find the
	// longest subarray with
	// an equal number of letters and numbers.

	static void max() {
		String arr = "x22h1b8km9811111111yewwe";
		int n = arr.length();
		System.out.println("Length of the longest 0 sum " + "subarray is " + maxLen(arr, n));
	}

	private static int maxLen(String arr, int n) {
		// TODO Auto-generated method stub
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(0, -1);

		int sum = 0;
		for (int i = 0; i < n; i++) {
			sum += getEncodedInt(arr.charAt(i));
			Integer old = map.get(sum);
			// System.out.println(".."+(arr.substring(0,i+1)));
			if (old != null) {
				String print = arr.substring(old + 1, i + 1);
				System.out.println(sum + "\t" + (print));
			} else {
				map.put(sum, i);
				// System.out.println(sum +"\t"+ (""));

			}
		}
		return -1;
	}

	private static int getEncodedInt(char c) {
		// System.out.println("is a digitt " +c + Character.isDigit(c));
		return Character.isDigit(c) ? 1 : -1;
	}

	// Shortest Supersequence: You are given two arrays, one shorter (with all
	// distinct elements)
	// and one longer. Find the shortest subarray in the longer array
	// that contains all the elements in the shorter
	// array. The items can appear in any order.
	// EXAMPLE
	// lnput:{1, 5, 9} I {7, 5, 9, 0, 2, 1, 3, 5, 7, 9. 1, 1, 5, 8, 8, 9, 7}

	void findSubArray() {
		int sub[] = new int[] { 1, 5, 9 };
		int arr[] = new int[] { 7, 5, 9, 0, 2, 1, 3, 5, 7, 9, 1, 1, 5, 8, 8, 9, 7 };
		findSubArray(arr, sub);
	}

	void findSubArray(int arr[], int sub[]) {
		HashSet<Integer> set = new HashSet<>();
		for (int i : sub)
			set.add(i);

		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			if (!set.contains(arr[i]))
				continue;
			map.put(arr[i], i);

			checkAndPrint(map, set);

		}
	}

	void checkAndPrint(HashMap<Integer, Integer> map, HashSet<Integer> set) {
		TreeSet<Integer> t = new TreeSet<Integer>();
		for (int i : set) {
			if (!map.containsKey(i)) {
				return;
			} else {
				t.add(map.get(i));
			}
		}
		System.out.println(t.toString() + "\t " + (t.last() - t.first()));
	}

	static void reverse() {
		String s = "sky is blue";
		int i = 0, j = s.length() - 1;

		char[] chars = s.toCharArray();

		while (i != j) {
			char c = s.charAt(i);
			chars[i] = chars[j];
			chars[j] = c;
			++i;
			--j;
		}

		i = 0;
		j = s.length() - 1;
		int start = 0;
		int space = 0;
		boolean flag = false;

		while (i != j) {
			char c = chars[i];
			if (Character.isWhitespace(c)) {
				reverse(chars, start, i - 1);
				start = i + 1;
			}
			++i;
			// --j;
		}

		if (!Character.isWhitespace(chars[chars.length - 1]))
			reverse(chars, start, chars.length - 1);
		System.out.println(chars);
	}

	static void reverse(char[] chars, int begin, int end) {
		System.out.println(chars);

		System.out.println("\t s " + begin + " end " + end);

		char temp;
		while (end > begin) {
			temp = chars[begin];
			chars[begin] = chars[end];
			chars[end] = temp;
			end--;
			begin++;
		}

	}

	// largest sum subarray in an array
	static void sumOfSubArray() {
		int[] arr = new int[] { -1, 887, 1, 2, -37, 4, 6, -87 };

		int msxsumfound = 0;
		int maxSum = arr[0];
		boolean down = false;
		int start = 0, end = 0;
		int downfall = 0;
		int result = 0;

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > 0 && !down) {
				msxsumfound += arr[i];
				end = i;
			} else {
				down = true;
				downfall += arr[i];// buffering

				if (downfall >= 0) {// escaped out of hell
					System.out.println(" escape ... at  " + i + " with down " + downfall);
					msxsumfound += downfall;
					downfall = 0;
					end = i;
					down = false;
				}

				if (msxsumfound + downfall < 0) {// doomed
					down = false;
					start = i + 1;
					downfall = 0;
					result = result > msxsumfound ? result : msxsumfound;
					msxsumfound = 0;
					System.out.println(" doom... ");
				}

			}
			System.out.println(" max ... i " + msxsumfound);

		}
		System.out.println(" max sum " + msxsumfound + " sttart " + start + "end " + end);
	}

	// You are building a diving board by placing a bunch of planks of wood
	// end-to-end.
	// There are two types of planks, one of length shorter and one of length
	// longer. You must use
	// exactly K planks of wood. Write a method to generate all possible lengths for
	// the diving board

	void numberofPlanks(int board, int small, int big) {
		if (board < small)
			return;

	}

	public static void main(String[] args) {
		subarray();
	}

	// Find subarray with given sum

	static void subarray() {

	}

	static void subarray(int arr[], int k) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int sum = 0;
		map.put(0, -1);
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			Integer get = map.get(sum);
			if((sum-))
			
			if (get != null) {

			} else
				map.put(sum, i);

		}

	}

	// English Int: Given any integer, print an English phrase that describes the
	// integer (e.g., "One Thousand,
	// Two Hundred Thirty Four").

	void descibe(int n) {
		String buffer="";
		if(n / 100 > 1)
			buffer += 
	}

	void getones(int i) {

		String[] ones = new String[] { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
		String[] teens = new String[] { "ten", "eleven", "twelve", "thirteen", "fourteen", "fifthteen", "eighteen",
				"nineteen" };

		String[] tens = new String[] { "", "", "twenty", "thirty", "fourty", "fifty", "sixty", "seventy", "eigty",
				"ninety" };
		String powers[] = { "Hundred", "Thousand", "Million", "Billon", "Trillon" };

	}
}
