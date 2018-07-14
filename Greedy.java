import java.util.Arrays;
import java.util.*;

public class Greedy {

	public static void main(String[] args) {
		Greedy g = new Greedy();
		g.railwayStation();
	}

	void railwayStation() {
		int arr[] = { 900, 940, 950, 1100, 1500, 1800 };
		int dep[] = { 910, 1200, 1120, 1130, 1900, 2000 };
		railwayStation(arr, dep);
	}

	LinkedHashMap<Integer, Integer> s = new LinkedHashMap<>();

	void railwayStation(int arr[], int dep[]) {
		Arrays.sort(arr);
		Arrays.sort(dep);

		int i = 0, j = 0;

		while (i != arr.length || j != dep.length) {

			if (i == arr.length) {
				changeCount(dep[j], false);
				j++;
				continue;
			} else if (j == dep.length) {
				changeCount(arr[i], true);
				i++;
				continue;
			}

			if (arr[i] <= dep[j]) {
				changeCount(arr[i], true);
				i++;
			} else {
				changeCount(dep[j], false);
				j++;
			}

		}
		printMax();

	}

	void printMax() {
		
		int max = 0;
		int running = 0;
		
		for (Map.Entry<Integer, Integer> entry : s.entrySet()) {
//			max = entry.getValue() > 0 ? entry.getValue() : max;
			running += entry.getValue();
			
			if (running > max)
				max = running;
			
			System.out.println(" running... \t" + "\t" + running);
		}
		System.out.println(" max... " + max);

	}

	void changeCount(int timeStamp, boolean arr) {
		Integer count = s.get(timeStamp);

		if (count == null)
			count = 0;

		s.put(timeStamp, arr ? count + 1 : count - 1);

	}

}
