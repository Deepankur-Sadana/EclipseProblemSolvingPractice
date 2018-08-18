import java.util.*;

public class Main {
	
	
	
	public static char[] removeDupes(char[] arr) {
		if (arr == null || arr.length < 2)
			return arr;
		
		int len = arr.length;
		int tail = 1;
		for (int x = 1; x < len; x++) {
			int y;
			for (y = 0; y < tail; y++) {
				if (arr[x] == arr[y])
					break;
			}
			if (y == tail) {
				arr[tail] = arr[x];
				tail++;
			}
		}
		return Arrays.copyOfRange(arr, 0, tail);
	}

	public static char[] bigArr(int len) {
		char[] arr = new char[len];
		Random r = new Random();
		String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*()-=_+[]{}|;:',.<>/?`~";

		for (int x = 0; x < len; x++) {
			arr[x] = alphabet.charAt(r.nextInt(alphabet.length()));
		}

		return arr;
	}

	public static void main(String args[]) {

		String result = new String(removeDupes(new char[] { 'a', 'b', 'c', 'd', 'a' }));
		assert "abcd".equals(result) : "abcda should return abcd but it returns: " + result;

		result = new String(removeDupes(new char[] { 'a', 'a', 'a', 'a' }));
		assert "a".equals(result) : "aaaa should return a but it returns: " + result;

		result = new String(removeDupes(new char[] { 'a', 'b', 'c', 'a' }));
		assert "abc".equals(result) : "abca should return abc but it returns: " + result;

		result = new String(removeDupes(new char[] { 'a', 'a', 'b', 'b' }));
		assert "ab".equals(result) : "aabb should return ab but it returns: " + result;

		result = new String(removeDupes(new char[] { 'a' }));
		assert "a".equals(result) : "a should return a but it returns: " + result;

		result = new String(removeDupes(new char[] { 'a', 'b', 'b', 'a' }));
		assert "ab".equals(result) : "abba should return ab but it returns: " + result;

		char[] arr = bigArr(5000000);
		long startTime = System.nanoTime();
		System.out.println("2: " + new String(removeDupes(arr)));
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println("Program took: " + duration + " nanoseconds");
		System.out.println("Program took: " + duration / 1000000000 + " seconds");

	}
}