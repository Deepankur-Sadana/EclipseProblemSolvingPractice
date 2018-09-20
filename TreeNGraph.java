import java.util.*;

public class TreeNGraph {

	public static void mainold(String[] args) {
		// TODO Auto-generated method stub
		TreeNGraph t = new TreeNGraph();
		// t.makeTTheGraph();

		// Node root = t.contructTree();
		// boolean b = t.isABalancedTree(root, 1);
		// System.out.println(" isBalanced " + b);
		List<Node> projects = t.buildProjects();
		t.printOrder(projects);

	}

	// Route Between Nodes: Given a directed graph,
	// design an algorithm to find out whether there is a
	// route between two nodes.

	class Node {
		public String name;
		int value;
		public Node[] children;
		Node left, right;
		
		Node vr;

		Node(String name, Node[] children) {
			this.name = name;
			this.children = children;
		}

		Node(String name) {
			this.name = name;
		}

		Node(int value) {
			this.value = value;
		}

		int[] leftLoad, rightLoad;
	}

	void makeTTheGraph() {
		Node n5 = new Node("5");
		Node n4 = new Node("4");
		Node n3 = new Node("3");
		Node n2 = new Node("2");
		Node n1 = new Node("1");
		Node n0 = new Node("0");

		n0.children = new Node[] { n1, n4, n5 };

		n1.children = new Node[] { n4, n3 };
		n2.children = new Node[] { n1 };
		n3.children = new Node[] { n2, n4 };
		n4.children = null;
		n5.children = null;

		System.out.println("route exists :- ");

		boolean b = routeExistsBFS(n0, n2);

		System.out.println("route exists :- n2,n4  " + b);

	}

	HashSet<Node> nodesTraversed = new HashSet<Node>();

	// DFS
	private boolean routeExists(Node start, Node end) {
		if (start == end)
			return true;

		if (start.children == null)
			return false;

		for (Node n : start.children)
			if (n == end)
				return true;
			else
				return routeExists(n, end);

		return false;
	}

	private boolean routeExistsBFS(Node start, Node end) {

		if (start == end)
			return true;

		if (start.children == null)
			return false;

		LinkedList<Node> q = new LinkedList<Node>();

		q.add(start);
		HashSet<Node> map = new HashSet<>();

		while (!q.isEmpty()) {
			Node poll = q.poll();
			map.add(poll);
			if (poll.children == null)
				continue;
			for (Node n : poll.children) {
				if (n == end)
					return true;
				if (!map.contains(n))
					q.add(n);
			}
		}

		return false;

	}

	boolean isABST(Node root) {
		return isABST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	boolean isABST(Node root, int min, int max) {

		if (root == null)
			return true;

		if (root.value < min || root.value > max)
			return false;

		return isABST(root.left, min, root.value - 1) && isABST(root.right, root.value + 1, max);
	}

	private void makeTreeFromArray(int[] arr) {
		LoadBalancer lb = new LoadBalancer(arr);
		Node root = new Node(lb.splitSpotValue);
		root.leftLoad = lb.leftLoad;
		root.rightLoad = lb.rightload;
		distributeLoadToChildren(root);

	}

	public static class LoadBalancer {

		int[] leftLoad;
		int[] rightload;
		int splitSpotValue;

		LoadBalancer(int[] arr) {
			int middle = arr.length / 2;
			splitSpotValue = arr[middle];
			leftLoad = Arrays.copyOfRange(arr, 0, splitSpotValue - 1);
			rightload = Arrays.copyOfRange(arr, splitSpotValue + 1, arr.length);
		}
	}

	private void distributeLoadToChildren(Node root) {

		if (root.leftLoad != null && root.leftLoad.length > 0) {
			LoadBalancer lb = new LoadBalancer(root.leftLoad);
			root.left = new Node(lb.splitSpotValue);

			root.left.leftLoad = lb.leftLoad;
			root.left.rightLoad = lb.rightload;

			distributeLoadToChildren(root.left);
		}

		if (root.rightLoad != null && root.rightLoad.length > 0) {
			LoadBalancer lb = new LoadBalancer(root.rightLoad);
			root.right = new Node(lb.splitSpotValue);

			root.right.leftLoad = lb.leftLoad;
			root.right.rightLoad = lb.rightload;

			distributeLoadToChildren(root.right);

		}
	}

	// returns the root
	Node contructTree() {
		Node n5 = new Node("5");
		Node n4 = new Node("4");
		Node n3 = new Node("3");
		Node n2 = new Node("2");
		Node n1 = new Node("1");

		Node n6 = new Node("6");
		Node n7 = new Node("7");
		Node n8 = new Node("8");
		Node n9 = new Node("9");

		n1.left = n2;
		n1.right = n3;

		n2.left = n4;
		n2.right = n5;

		n3.left = n6;
		n3.right = n7;

		n4.left = n8;
		n4.right = n9;

		traverse(n1, n5);
		return n1;

	}

	HashMap<Integer, HashSet<Node>> map = new HashMap<>();

	void getAllNodesAtALevel(Node root) {

		searchChildren(root, 1);
		Iterator iterattor = map.entrySet().iterator();

		while (iterattor.hasNext()) {
			Map.Entry pair = (Map.Entry) iterattor.next();
			String b = "";
			for (Node n : (HashSet<Node>) pair.getValue()) {
				b += n.name;
				b += "";
			}
			System.out.println(" value at " + pair.getKey() + " is :- " + b);
		}
	}

	void searchChildren(Node root, int level) {

		if (root == null)
			return;

		HashSet<Node> set = map.get(level);
		if (set == null)
			set = new HashSet<>();
		set.add(root);
		map.put(level, set);

		searchChildren(root.left, level + 1);
		searchChildren(root.right, level + 1);

	}

	HashSet<Integer> set = new HashSet<>();

	boolean isABalancedTree(Node root, int level) {
		goDeep(root, level);
		return set.size() <= 2;
	}

	void goDeep(Node root, int level) {

		if (root.left == null || root.right == null) {
			set.add(level);
			System.out.println("adding to set " + level);
		}

		if (root.left != null)
			goDeep(root.left, level + 1);

		if (root.right != null)
			goDeep(root.right, level + 1);

	}

	Node findSuccesInInOrderTraversal(Node root, Node processing) {

		return null;
	}

	boolean next = false;

	void traverse(Node root, Node success) {

		if (root == null)
			return;

		traverse(root.left, success);
		if (root == success) {
			next = true;

		}
		if (next) {
			System.out.println("successe :- " + root.name);
		}

		System.out.println("val :- " + root.name);
		traverse(root.right, success);
	}

	List<Node> buildProjects() {
		Node a = new Node("a");
		Node b = new Node("b");
		Node c = new Node("c");
		Node d = new Node("d");
		Node e = new Node("e");
		Node f = new Node("f");

		// (a, d), (f, b), (b, d), (f, a), (d, c)
		a.children = new Node[] { f };
		b.children = new Node[] { f };
		c.children = new Node[] { d };
		d.children = new Node[] { a, b };

		List<Node> list = new ArrayList<>();
		list.add(a);
		list.add(b);
		list.add(c);
		list.add(d);
		list.add(e);
		list.add(f);
		return list;

	}

	void printOrder(List<Node> list) {

		boolean moreIndependentProjectsExists = true;
		HashSet<Node> independentProjects = new HashSet<>();

		List<Node> listLeftToTTraverse = new ArrayList<>();
		listLeftToTTraverse.addAll(list);

		while (moreIndependentProjectsExists) {
			// find if actually independent projects exists
			// avoid infinite loop
			moreIndependentProjectsExists = false;

			Iterator Iterator = listLeftToTTraverse.iterator();

			while (Iterator.hasNext()) {
				Node n = (Node) Iterator.next();
				if (n.children == null || n.children.length == 0) {
					independentProjects.add(n);
					Iterator.remove();
					System.out.println(" adding " + n.name);
					moreIndependentProjectsExists = true;
				}
			}

			System.out.println(" pre shrink ");
			// removing reference as a children of all independent projects
			for (Node n : independentProjects) {
				System.out.println(" shrink " + n.name);
				for (Node l : list) {
					l.children = shrink(l.children, n);
				}
			}
		}

		String s = " ";
		for (Node n : independentProjects) {
			s += n.name;
			s += " ";
		}
		System.out.println(" independentProjects...... " + s);
	}

	Node[] shrink(Node[] arr, Node delete) {

		System.out.println(" method ");
		if (arr == null || arr.length == 0)
			return null;

		ArrayList<Node> list = new ArrayList<>();
		for (Node n : arr)
			list.add(n);

		if (!list.contains(delete))
			return arr;
		list.remove(delete);
		return list.toArray(new Node[list.size()]);

	}

	void connectNodeAtSameLevel(Node root, HashMap<Integer, Stack<Node>> levelMap) {
		IOT(root,0,levelMap);
		for(Map.Entry<Integer, Stack<Node>> entry:levelMap.entrySet()) {
			Node pre = null;
			while(!entry.getValue().isEmpty()) {
				Node pop = entry.getValue().pop();
				pop.vr=pre;
				pre=pop;
			}
		}
		
	}

	void IOT(Node root, int level, HashMap<Integer, HashSet<Node>> levelMap) {
		if (root == null)
			return;
		checkAndInitializeMap(level, levelMap);
		IOT(root.left, level + 1, levelMap);
		levelMap.get(level).add(root);
		IOT(root.right, level + 1, levelMap);

	}

	void checkAndInitializeMap(int level, HashMap<Integer, HashSet<Node>> levelMap) {
		if (levelMap.get(level) == null)
			levelMap.put(level, new HashSet<Node>());
	}
	



}
