import java.awt.Point;
import java.util.*;

public class DP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// child and steps
	private void countWays(int numerOfSteps, int[] steps) {

	}

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

}
