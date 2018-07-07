import java.util.*;

public class BinaryTree {

	Node root;
	int totalNodes;

	BinaryTree(int rootValue) {

		root = new Node(rootValue);
		totalNodes = 1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryTree b = new BinaryTree(4);

	}

	// insert, find, and delete, has a method getRandomNode()
	class Node {
		Node left, right;
		int value;
		int leftCount, rightCount;

		Node(int value) {
			this.value = value;
		}
	}

	void insert(int val) {
		++totalNodes;
		insert(root, val);
	}

	void insert(Node root, int val) {
		if (root.value <= val) {
			if (root.left != null) {
				root.leftCount++;
				insert(root.left, val);
			} else {
				Node left = new Node(val);
				root.left = left;
				root.leftCount++;
			}
		} else {
			if (root.right != null) {
				root.rightCount++;
				insert(root.right, val);
			} else {
				Node right = new Node(val);
				root.right = right;
				root.rightCount++;
			}
		}
	}

	Node getRandomNode(Node root) {
		int total = root.leftCount + root.rightCount + 1;
		//0 to total
		//0-root; (1 to leftCount) l; (leftCountt - total) r
		Random ran = new Random();
		int x = ran.nextInt(total) + 0;
//		The integer x is now the random number that has a possible outcome of 5-10
		if(x==0)
			return root;
		else if (x >=1 && x <root.leftCount) {
			return getRandomNode(root.left);
		}else {
			return getRandomNode(root.right);
		}
	}

}
