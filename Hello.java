import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public class Hello {

	// Is Unique: Implement an algorithm to determine if a string has all unique
	// characters. What if you
	// cannot use additional data structures?
	// Hints: #44, #7 7 7, #732
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(".....start...");

		String s = "sdsda";
		for (int i = 0; i < s.length(); i++) {
			HashSet<String> map = new HashSet<>();
			int e = s.charAt(i);
			boolean b = map.add(s.substring(i, i + 1));

			// System.out.println("..... "+s.substring(i, i+1)+"\t"+b);
		}

		int mat[][] = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
		deleteDuplicateinList();
	}

	static void deleteDuplicateinList() {
		LinkedList<Node> object = new LinkedList<Node>();

		object.add(new Node(3));
		// Adding elements to the linked list
		Node head = object.getFirst();

		head.appendToTail(2);
		head.appendToTail(3);
		Node delet = head.appendToTail(39);

		head.appendToTail(1);

		System.out.println("Linked list : " + head.data);
		HashSet h = new HashSet<Integer>();
		printList(object.getFirst());

		Node n = head;

		System.out.println(" \n \n ");
		// removeKElementFromLast(object, 4);
		// deleteMiddleNode(object, delet);
		// printList(object.getFirst());
		printSum(getListArray());
	}

	private static void removeKElementFromLast(LinkedList<Node> object, int k) {
		Node currentNode = null;
		// System.out.println(" ...getCount.. " +object.size() );
		int count = getCount(object.getFirst());
		;
		for (int i = 0; i < count; i++) {
			if (currentNode == null)
				currentNode = object.getFirst();
			else {
				currentNode = currentNode.next;
			}

			System.out.println(" ..... " + i);
			if (i == count - (k + 1)) {
				System.out.println(" ...inner. " + i);
				System.out.println(" i..... " + i);
				if (currentNode.next != null)
					currentNode.next = currentNode.next.next;
				else
					currentNode.next = null;

				break;
			}

		}

	}

	public static LinkedList<Node>[] getListArray() {
		// Input: (7-> 1 -> 6) + (5 -> 9 -> 2).That is,617 + 295.

		LinkedList<Node>[] arr = new LinkedList[2];
		LinkedList<Node> l1 = new LinkedList<Node>();

		l1.add(new Node(7));
		Node h1 = l1.getFirst();
		h1.appendToTail(1);
		h1.appendToTail(6);

		LinkedList<Node> l2 = new LinkedList<Node>();

		l2.add(new Node(5));
		Node h2 = l2.getFirst();
		h2.appendToTail(9);
		h2.appendToTail(2);

		arr[0] = l1;
		arr[1] = l2;

		return arr;
	}

	public static void printSum(LinkedList<Node>[] arr) {
		int[] digits = new int[arr.length];
		int totalsum = 0;
		for (int i = 0; i < arr.length; i++) {
			LinkedList<Node> list = arr[i];
			Node n = list.getFirst();
			int mult = 1;
			while (n != null) {
				digits[i] += mult * n.data;
				mult *= 10;
				n = n.next;
			}
			totalsum += digits[i];
		}

		System.out.println("sum = " + totalsum);
		LinkedList<Node> list = new LinkedList<Node>();
		list.addFirst(new Node(totalsum / 10));
		totalsum /= 10;
		Node n = list.getFirst();
		while (totalsum / 10 > 0) {
			totalsum /= 10;
			n = n.next;
			totalsum /= 10;
		}
		// printList(list.getFirst());
		LinkedList<Node> buildPalinrome = buildPalinrome();
		printList(buildPalinrome.getFirst());
		boolean b = isPalinrome(buildPalinrome.getFirst());
		System.out.println("isPalinrome " + b);
	}

	public static boolean deleteMiddleNode(LinkedList<Node> object, Node n) {
		int count = getCount(object.getFirst());
		if (n == null || n.next == null) {
			return false; // Failure
		}
		Node next = n.next;
		n.data = next.data;
		n.next = next.next;
		return false;

	}

	public static int getCount(Node head) {

		Node temp = head;
		int count = 0;
		while (temp != null) {
			count++;
			temp = temp.next;
		}
		return count;
	}

	private static LinkedList<Node> buildPalinrome() {
		LinkedList<Node> object = new LinkedList<Node>();

		object.add(new Node(1));

		// Adding elements to the linked list
		Node head = object.getFirst();

		head.appendToTail(2);
		head.appendToTail(3);
//		head.appendToTail(2);

		head.appendToTail(1);

		return object;
	}

	private static boolean isPalinrome(Node head) {
		Stack<Node> stack = new Stack<>();

		Node n = head;
		// while (head.next != null) {
		// n = head.next;
		// stack.push(n);
		// }

		ArrayList<Node> list = new ArrayList<>();
		list=addtoArrayFromLink(n,list);

//		System.out.println("size " + list.size());

		int j;
		for (int i = 0; i < list.size() / 2; i++) {
			j = list.size() - (i + 1);
//			System.out.println("i " + list.get(i).data + "j " + list.get(j).data);
			if (list.get(i).data != list.get(j).data)
				return false;
		}
		return true;

	}

	private static ArrayList<Node> addtoArrayFromLink(Node n, ArrayList<Node> arr) {
		if (n != null) {
			arr.add(n);
			n=n.next;
			addtoArrayFromLink(n,arr);

		}
		return arr;
	}

	private static void printList(Node head) {
		Node tnode = head;
		while (tnode != null) {
			System.out.print(tnode.data + " \n ");
			tnode = tnode.next;
		}
	}

	/* Linked list Node */
	public static class Node {
		int data;
		Node next = null;

		Node(int d) {
			data = d;
			next = null;
		}

		Node appendToTail(int d) {
			Node end = new Node(d);
			Node n = this;
			while (n.next != null) {
				n = n.next;
			}

			n.next = end;
			return end;
		}

		void deleteNode(Node node) {
			// Store head node
			if (next != null) {
				next.next = node.next;
			}
		}

	}
}
