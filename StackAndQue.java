import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

public class StackAndQue {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		avoid(null);
	}

	static void showpush(Stack st, int a) {
		st.push(new Integer(a));
		System.out.println("push(" + a + ")");
	}

	static void showpop(Stack st) {
		System.out.print("pop -> ");

		// Integer a = (Integer) st.pop();

		// System.out.println(a);

		System.out.println("stack: " + st);

	}

	static ArrayList<Stack<Item>> stackList = new ArrayList<>();
	static int stackSize = 5;

	public static void avoid(String args[]) {

		int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7, 12, 1, 1, 3, 46, 78, 1, 2, 9, 0, 9, 0};
		Stack<Integer> stack = new Stack<>();

		for (int i : arr) {
			stack.push(i);
		}
		printStack(stack);
		reverseStack(stack);
		printStack(stack);
	}

	static void insertIntoMem(Item item) {
		if (stackList.size() == 0) {
			stackList.add(new Stack<Item>());
		}

		Stack<Item> stack = stackList.get(stackList.size() - 1);
		if (stack.size() == stackSize) {
			stackList.add(new Stack<Item>());
			stack = stackList.get(stackList.size() - 1);
		}

		stack.push(item);

	}

	private static void printallStacks() {
		System.out.println("printing " + stackList.size());
		for (int i = 0; i < stackList.size(); i++) {
			Stack<Item> s = stackList.get(i);
			Iterator<Item> iter = s.iterator();
			String builder = "";

			while (iter.hasNext()) {
				builder += iter.next().val;
				builder += " , ";
			}
			System.out.println(builder + "\n");

		}
	}

	private static void printStack(Stack<Integer> s) {
		Iterator<Integer> iter = s.iterator();
		String builder = "";

		while (iter.hasNext()) {
			builder += iter.next();
			builder += " , ";
		}

		System.out.println(builder + "\n");
	}

	public static Item popxStack(int index) {
		if (index > stackList.size() - 1)
			return null;

		Stack<Item> stack = stackList.get(stackList.size() - 1);
		return null;

	}

	public static class Item {
		int val;

		Item(int val) {
			this.val = val;
		}
	}

	// 3.4 Queue via Stacks: Implement a MyQueue class which implements a queue
	// using two stacks.
	class MyQueue<E> extends Stack<E> {

	}

	public static void reverseStack(Stack<Integer> stack) {
		Stack<Integer> temp = new Stack<>();
		int size = getStackSize(stack);

		int iterationsDone = 0;

		Integer currentItem = null;
		while (iterationsDone < size) {
			iterationsDone++;

			// popping the left stack start
			while (!stack.isEmpty()) {
				
				int popped = stack.pop();

				if (currentItem == null)
					currentItem = popped;

				if (popped < currentItem)
					currentItem = popped;

				temp.push(popped);
			}
			System.out.println("currentItem " + currentItem);
			// all items popped and pushed to temp
			
			boolean found = false;

			for (int i = 0; i < size - iterationsDone; i++) {
				
				System.out.println("size - iterationsDone " + (size - iterationsDone));
				int popped = temp.pop();

				if (!found) {
					if (popped == currentItem) {
						// do nothing. this item we'll add later
						found = true;
					} else {
						stack.push(popped);
					}
				} else {
					stack.push(popped);
				}
			}
			
			System.out.println("push " + currentItem);

			temp.push(currentItem);
		}
		
		printStack(temp);
	}

	static int getStackSize(Stack<Integer> stack) {
		return stack.size();
	}

}
