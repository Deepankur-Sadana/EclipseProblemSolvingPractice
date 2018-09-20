
import java.util.*;

public class GNode<T> {

	private T t;
	public GNode next;

	public T get() {
		return this.t;
	}

	public void set(T t1) {
		this.t = t1;
	}

	public static void main(String args[]) {

		String input = "hello world, this is a line of text";
//		getFirstWord(input);
//		changeBase(64, 2);
		genNumber(123);

	}

	private static void genNumber(final int N) {
		int k = 0;
		while (true) {
			int result = Integer.parseInt(Integer.toBinaryString(k));
			if (result > N) {
				break;
			}
			System.out.print(result + " ");
			k++;
		}
	}

	private static void getFirstWord(String text) {
		String f, l;
		if (text.contains(" ")) {
			f = l = text;
		}
		int sI = text.indexOf(' ');
		f = text.substring(0, sI);

		int lI = text.lastIndexOf(' ');
		l = text.substring(lI + 1, text.length());

		System.out.println("first " + f + " last " + l);
		HashMap<String, String> map;
	}

	static void changeBase(int n, int base) {
		Stack<Integer> stack = new Stack<Integer>();
		int remainder = n;

		while (remainder != 0) {
			stack.push(remainder % base);
			remainder = remainder / base;
//			System.out.println(remainder);
		}
		while (!stack.isEmpty()) {
			System.out.print(stack.pop());
		}

	}

	void shuffleArray(int arr[]) {
		for (int i = arr.length - 1; i >= 0; i--) {
			int index = getRandomNumber(0, i);
			swap(index, i, arr);
		}
	}

	void swap(int l, int r, int[] arr) {
		int temp = arr[l];
		arr[l] = arr[r];
		arr[r] = temp;
	}

	// lower inclusive
	// upper inclusive
	int getRandomNumber(int lower, int upper) {
		return 0;
	}
}
