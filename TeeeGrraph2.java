import java.util.*;

public class TeeeGrraph2 {

	public static void main(String[] args) {
		TeeeGrraph2 t = new TeeeGrraph2();

		Node root =t.makeTree();
		t.printLeftView(root, 1, new HashMap<Integer, Boolean>());
	}



	public static class Node {
		Node left, right;
		int data;

		Node(int data) {
			this.data = data;
		}
	}

	static Node makeTree() {
		Node n4 = new Node(4);

		n4.left = new Node(5);
		n4.right = new Node(2);
		n4.right.left = new Node(3);
		n4.right.right = new Node(1);

		n4.right.left.left = new Node(6);
		n4.right.left.right = new Node(7);
		n4.right.left.right.right = new Node(8);
		n4.right.left.right.left = new Node(12);

		return n4;

	}

	 void printLeftView(Node root, int level, HashMap<Integer, Boolean> map) {
		if (root == null) {
			return;
		}

		if (map.get(level) == null) {
			System.out.println("data " + root.data);
			map.put(level, true);
		}
		printLeftView(root.left, level + 1, map);
		printLeftView(root.right, level + 1, map);

	}

}
