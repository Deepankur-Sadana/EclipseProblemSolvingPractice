import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;

import Hello.Node;

public class AnimalShelter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		insertAnimals();
		getMeAnimal(null);
		printList();

	}

	/* Linked list Node */
	public static class Node {
		Animal data;
		Node next = null;

		int index;

		Node(Animal d) {
			data = d;
			next = null;
		}

		Node appendToTail(Animal d) {
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

	public enum Type {
		CAT, DOG
	}

	public static class Animal {

		Type type;
		String name;

		Animal(Type type, String name) {
			this.type = type;
			this.name = name;
		}
	}

	static Stack<Node> stack = new Stack<Node>();
	static LinkedList<Node> linkedlist = new LinkedList<Node>();

	static LinkedList<Node> dogList = new LinkedList<Node>();
	static LinkedList<Node> catList = new LinkedList<Node>();

	static void insertAnimals() {
		stack.add(new Node(new Animal(Type.CAT, "1")));
		stack.add(new Node(new Animal(Type.CAT, "2")));
		stack.add(new Node(new Animal(Type.CAT, "3")));
		stack.add(new Node(new Animal(Type.CAT, "4")));

		stack.add(new Node(new Animal(Type.DOG, "5")));
		stack.add(new Node(new Animal(Type.DOG, "6")));
		stack.add(new Node(new Animal(Type.DOG, "7")));
		stack.add(new Node(new Animal(Type.DOG, "8")));

	}

	static void getMeAnimal(Type type) {
		int count = 1;
		while ((!stack.isEmpty())) {
			Node n = stack.pop();
			++count;
			n.index = count;
			linkedlist.add(n);
		}

		System.out.println("list size " + linkedlist.size());

		ListIterator<Node> listIterator = linkedlist.listIterator();

		while (listIterator.hasNext()) {
			
			Node n = listIterator.next();
			System.out.println(n.data.name + "\t" + n.data.type);
			if (type == null) {
				listIterator.remove();
				break;
			}
		}
	}

	static void printList() {
		ListIterator<Node> listIterator = linkedlist.listIterator();
		// linkedlist.peek();

		while (listIterator.hasNext()) {
			Node n = listIterator.next();
			System.out.println(n.data.name + "\t" + n.data.type);
			// listIterator.next().data.type);
		}
	}

}
