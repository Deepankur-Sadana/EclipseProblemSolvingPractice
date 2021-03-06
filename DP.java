import java.awt.Point;
import java.util.*;

public class DP {

	ArrayList<Point> lockedTiles = new ArrayList<>();

	final static int HEIGHT = 4, WIDTH = 4;

	void initBreaks() {
		lockedTiles.add(new Point(2, 2));
		lockedTiles.add(new Point(2, 1));
		lockedTiles.add(new Point(1, 1));
	}

	class Node {
		Node top, left;
		Point location;
		boolean deadEnd = true;

		Node() {

		}
	}

	void getPath() {

		boolean pathFound = false;
		Point bottomRight = new Point(WIDTH - 1, HEIGHT - 1);
		Node startingNode = new Node();
		startingNode.location = bottomRight;
		fillPaths(startingNode);
	}

	void fillPaths(Node root) {
		if (root.location.getX() == 0 && root.location.getY() == 0) {
			System.out.println(" found a happy path ....");
			return;
		}

		if (canMoveUp(root)) {
			addUpperNode(root, getTopTile(root.location));
			fillPaths(root.top);
		}

		if (canMoveLeft(root)) {
			addLeftNode(root, getLeftTile(root.location));
			fillPaths(root.left);
		}
	}

	void addUpperNode(Node root, Point upper) {
		Node top = new Node();
		top.location = upper;
		root.top = top;
	}

	void addLeftNode(Node root, Point left) {
		Node l = new Node();
		l.location = left;
		root.left = l;
	}

	boolean canMoveLeft(Node n) {
		if (getLeftTile(n.location) != null)
			return true;
		return false;
	}

	boolean canMoveUp(Node n) {
		if (getTopTile(n.location) != null)
			return true;
		return false;
	}

	Point getTopTile(Point p) {
		if (p.getY() == 0)
			return null;
		Point top = new Point();
		top.move(p.x, p.y - 1);

		if (!isABlockedTile(top)) {
			return top;
		}
		return null;
	}

	Point getLeftTile(Point p) {
		if (p.getX() == 0)
			return null;
		Point left = new Point();
		left.move(p.x - 1, p.y);

		if (!isABlockedTile(left)) {
			return left;
		}
		return null;
	}

	boolean isABlockedTile(Point p) {
		for (Point tile : lockedTiles) {
			if (p.getX() == tile.getX() && p.getY() == tile.getY())
				return true;
		}
		return false;
	}

	void search() {

		int arr[] = { 2, 3, 4, 10, 40 };
		int n = arr.length;
		int x = 10;
		int result = binarySearch(arr, 0, n - 1);
		if (result == -1)
			System.out.println("Element not present");
		else
			System.out.println("Element found at index " + result);
	}

	int binarySearch(int arr[], int l, int r) {
		if (r >= l) {
			int mid = l + (r - l) / 2;

			// If the element is present at the
			// middle itself
			if (arr[mid] == mid)
				return mid;

			// If element is smaller than mid, then
			// it can only be present in left subarray
			if (arr[mid] > mid)
				return binarySearch(arr, l, mid - 1);

			// Else the element can only be present
			// in right subarray
			return binarySearch(arr, mid + 1, r);
		}

		// We reach here when element is not present
		// in array
		return -1;
	}

	void printSlidinWindow() {
		printSlidinWindowDequeu(new int[] { 8, 5, 10, 7, 9, 4, 15, 12, 90, 13 }, 3);
	}

	void printSlidinWindow(int[] arr, int window) {

		if (window > arr.length)
			return; // base case
		BinarySearchTree tree = new BinarySearchTree();

		Deque<Integer> q = new ArrayDeque<>();

		for (int i = 0; i < arr.length; i++) {
			if (i < window) {
				tree.insert(arr[i]);
				if (i == window - 1)
					System.out.println("max ...." + tree.getMax());
			} else {

				int remove = arr[i - (window)];
				tree.delete(remove);

				tree.insert(arr[i]);
				int max = tree.getMax();
				System.out.println("max " + max);

			}
		}
	}

	void printSlidinWindowDequeu(int[] arr, int window) {

		if (window > arr.length)
			return; // base case

		Deque<Integer> q = new ArrayDeque<>();

		for (int i = 0; i < arr.length; i++) {
			if (i < window) {
				if (q.isEmpty())
					q.add(i);
				else {
					while (!q.isEmpty() && arr[i] >= arr[q.getLast()]) {
						q.removeLast();
					}
					q.add(i);
				}
				System.out.println(Arrays.asList(q));
				if (i == window - 1)
					System.out.println("max .... " + arr[q.getFirst()]);
			} else {
				if (q.getFirst() == i - window)
					q.removeFirst();

				while (!q.isEmpty() && arr[i] >= arr[q.getLast()]) {
					q.removeLast();
				}
				q.add(i);
				System.out.println("max ...." + arr[q.getFirst()]);
			}
		}

	}

	void printLCS() {
		knapster01();

	}

	void printEditDistance(String one, String two) {
		int dp[][] = new int[one.length() + 1][two.length() + 1];

		for (int i = 0; i < dp[0].length; i++)
			dp[0][i] = i;

		for (int i = 0; i < dp.length; i++)
			dp[i][0] = i;

		Utils.pint(dp);
		for (int r = 1; r < dp.length; r++) {
			for (int c = 1; c < dp[0].length; c++) {
				System.out.println(".. " + r + " " + c + " ");
				if (one.charAt(r - 1) == two.charAt(c - 1)) {
					dp[r][c] = dp[r - 1][c - 1];
				} else {
					dp[r][c] = getMinimum(dp[r - 1][c], dp[r][c - 1], dp[r - 1][c - 1]) + 1;
				}
			}
		}
		Utils.pint(dp);
	}

	int getMinimum(int a, int b, int c) {
		return Math.min(Math.min(a, b), c);
	}

	void knapster01() {
		knapster01(new int[] { 1, 2, 3, 5 }, new int[] { 2, 5, 8, 15 }, 6);

		int val[] = { 60, 100, 120 };
		int wt[] = { 10, 20, 30 };
		int W = 50;

		// knapster01(wt, val, W);
	}

	void knapster01(int[] weight, int[] value, int loot) {
		if (weight.length != value.length)
			return;// bad input

		int items = value.length;
		int dp[][] = new int[items][loot + 1];

		for (int i = 0; i <= loot; i++) {
			dp[0][i] = i < weight[0] ? 0 : value[0];
		}

		for (int r = 1; r < items; r++) {
			for (int c = 1; c <= loot; c++) {
				dp[r][c] = c < weight[r] ? dp[r - 1][c] : dp[r][c - weight[r]] + value[r];

			}
		}
		Utils.pint(dp);
	}

	class Job {
		String name;
		int start, end, weight;
		int combinedweight;

		Job(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
			this.combinedweight = weight;
		}

		public String toString() {
			return name + " start " + start + " end " + end + " combined " + combinedweight;
		}
	}

	public static void main(String[] args) {
		DP d = new DP();
		d.weightedJobScheduling();

	}

	void weightedJobScheduling() {
		Job jobs[] = new Job[6];
		jobs[0] = createJob(1, 3, 5);
		jobs[1] = createJob(2, 5, 6);
		jobs[2] = createJob(4, 6, 5);
		jobs[3] = createJob(6, 7, 4);
		jobs[4] = createJob(5, 8, 11);
		jobs[5] = createJob(7, 9, 2);
		weightedJobScheduling(jobs);
		printJobs(jobs);
		printMax(jobs);
	}

	Job createJob(int start, int end, int weight) {
		return new Job(start, end, weight);
	}

	void weightedJobScheduling(Job[] jobs) {
		int i = 1, j = 0;

		while (i != jobs.length) {

			j = 0;
			while (j < i) {
				Job first = jobs[j];
				Job second = jobs[i];
				if (!doesOverLap(first, second)) {
					updateMaxWeight(first, second);
				}
				j++;
			}
			i++;
		}
	}

	// sorted based on end time
	boolean doesOverLap(Job first, Job second) {

		if (first.end > second.start)
			return true;
		else
			return false;
	}

	void updateMaxWeight(Job first, Job second) {
		int proposedW = first.combinedweight + second.weight;
		if (second.combinedweight < proposedW)
			second.combinedweight = proposedW;
	}

	void printMax(Job... jobs) {
		int max = 0;
		for (Job j : jobs)
			if (j.combinedweight > max)
				max = j.combinedweight;
		
		System.out.println("max..." +max);
	}

	void printJobs(Job[] jobs) {
		for (Job job : jobs)
			System.out.println(job);
	}

}
