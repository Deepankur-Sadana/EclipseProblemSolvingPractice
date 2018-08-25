import java.util.*;

public class HackerRank {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	static void reverseShuffleMerge(String s) {
		Queue<Character> queue = new PriorityQueue<>(idComparator);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			queue.add(c);
		}

	}

	public static Comparator<Character> idComparator = new Comparator<Character>() {

		@Override
		public int compare(Character c1, Character c2) {
			return (c1 - c2);
		}
	};
	

	void addToMap(String s) {
		LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			Integer count = map.get(c);

			if (count == null)
				count = 0;

			map.put(c, ++count);
		}
	}

	boolean isRequired(char c, LinkedHashMap<Character, Integer> destMap, String built) {
		LinkedHashMap<Character, Integer> copy = new LinkedHashMap<Character, Integer>();

		for (Map.Entry<Character, Integer> entry : destMap.entrySet()) {
			copy.put(entry.getKey(), entry.getValue());
		}

		for (int i = 0; i < built.length(); i++) {
			Integer count = copy.get(built.charAt(i));
			copy.put(c,--count);
		}
		return copy.get(c) > 0;

	}

}
