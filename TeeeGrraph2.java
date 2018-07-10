import java.util.*;

public class TeeeGrraph2 {

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

	Node buildBottomViewTree() {
		Node n20 = new Node(20);
		n20.right = new Node(22);
		n20.right.right = new Node(25);
		n20.right.left = new Node(4);

		n20.left = new Node(8);
		n20.left.left = new Node(5);
		

		n20.left.right = new Node(3);
		n20.left.right.left = new Node(10);
		n20.left.right.right= new Node(14);

		return n20;
	}

	/**
	 * 
	 *                20
                    /    \
                  8       22
                /   \      \
              5      3      25
                    / \      
                  10    14

	 * @param args
	 */

	public static void main(String[] args) {
		TeeeGrraph2 t = new TeeeGrraph2();

		Node root = t.makeTree();
		t.printBottomView();
	}

	void printBottomView() {
		Node root = buildBottomViewTree();
		traverse(root, 1, 0);
		printBottomOrder();
	}

	TreeMap<Integer, TreeMap<Integer, Integer>> map = new TreeMap<>(); // <horizontal.<level,data>> map

	void traverse(Node root, int level, int horizontalDistance) {
		if (root == null)
			return;
		traverse(root.left, level + 1, horizontalDistance - 1);
		System.out.println("" + root.data);
		addNodeToMap(root,level,horizontalDistance);
		traverse(root.right, level + 1, horizontalDistance + 1);
	}

	void addNodeToMap(Node root, int level, int hor) {
		if (map.get(hor) == null)
			map.put(hor, new TreeMap<Integer, Integer>(Collections.reverseOrder()));

		map.get(hor).put(level, root.data);
	}

	void printBottomOrder() {
		for (Map.Entry<Integer, TreeMap<Integer, Integer>> column : map.entrySet()) {
			for(Map.Entry<Integer, Integer> element:column.getValue().entrySet()) {
				System.out.println("---"+element.getValue());
				break;
			}

		}

	}
}
