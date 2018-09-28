import java.util.*;

public class LeetCode {

	public int maxCoins(int[] nums) {
		ArrayList<Integer> list = new ArrayList<Integer>();

		for (int i : nums) {
			list.add(i);
		}

		int sum = 0;
		while (list.size() > 0) {
			System.out.println("max ");
			int index = getMinIndex(list);
			if (index == -1)
				continue;
			int coins = 1;
			coins *= list.get(index);
			coins *= index == 0 ? 1 : list.get(index - 1);
			coins *= index == list.size() - 1 ? 1 : list.get(index + 1);
			sum += coins;
			System.out.println(list.remove(index));

		}

		return sum;

	}

	int getMinIndex(ArrayList<Integer> list) {
		int index = -1;
		int secondLowest = -1;

		int min = Integer.MAX_VALUE;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) < min) {
				min = list.get(i);
				secondLowest = index;
				index = i;
			}
		}
		if ((index == 0 || index == list.size() - 1) && list.size() > 2) {
			return secondLowest;
		}
		return index;
	}

	void swim() {
		int[][] grid = new int[][] { { 0, 1, 2, 3, 4 }, { 24, 23, 22, 21, 5 }, { 12, 13, 14, 15, 16 },
				{ 11, 17, 18, 19, 20 }, { 10, 9, 8, 7, 6 } };
		swimInWater(grid);
	}

	public int swimInWater(int[][] grid) {
		boolean[][] marked = new boolean[grid.length][grid[0].length];
		int[][] min = new int[grid.length][grid[0].length];

		for (int row = 0; row < min.length; row++) {
			for (int col = 0; col < min[row].length; col++) {
				min[row][col] = Integer.MAX_VALUE;
				// min[row][col] = grid[row][col];
			}
		}
		min[0][0] = grid[0][0];
		Queue<Point> queue = new LinkedList<Point>();
		swim(new Point(0, 0), grid, min, marked, queue);
		System.out.println(" swim " + min[grid.length - 1][grid[0].length - 1]);
		return min[grid.length - 1][grid[0].length - 1];
		// return 16;
	}

	void swim(Point p, int[][] grid, int[][] dis, boolean[][] marked, Queue<Point> queue) {

		marked[p.r][p.c] = true;
//                System.out.println(" r "+p.r+" c "+p.c);

		Point[] neighbours = getNeighbours(p);

		for (Point n : neighbours) {
			if (!isValidPoint(n, grid))
				continue;
			if (!isOptimal(grid, dis, n, p))
				continue;
			queue.add(n);
			dis[n.r][n.c] = Math.min(dis[n.r][n.c], Math.max(grid[n.r][n.c], dis[p.r][p.c]));
		}

		while (!queue.isEmpty())
			swim(queue.poll(), grid, dis, marked, queue);

	}

	boolean isOptimal(int[][] grid, int[][] dis, Point end, Point start) {
		Point p = start, n = end;
		if (dis[end.r][end.c] > dis[start.r][start.c] && grid[end.r][end.c] < dis[start.r][start.c])
			return true;

		return false;

	}

	Point[] getNeighbours(Point p) {
		Point[] points = new Point[] { new Point(p.r - 1, p.c), // top
				new Point(p.r, p.c - 1), // left
				new Point(p.r, p.c + 1), // right
				new Point(p.r + 1, p.c) };// bottom
		return points;
	}

	boolean isValidPoint(Point p, int[][] grid) {
		return p.r >= 0 && p.c >= 0 && p.r < grid.length && p.c < grid[0].length;
	}

	static class Point {
		int r, c;

		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}

	public boolean isMatch(String s, String p) {
		boolean[][] arr = new boolean[s.length() + 1][p.length() + 1];
		arr[0][0] = true;
		for (int i = 1; i < arr[0].length; i++) {

			if (p.charAt(i - 1) == '*') {
				arr[0][i] = arr[0][i - 1];

			}
		}

		for (int i = 1; i < arr.length; i++) {
			for (int j = 1; j < arr[0].length; j++) {
				char target = s.charAt(i - 1);
				char pattern = p.charAt(j - 1);
				if (target == pattern || pattern == '?') {
					arr[i][j] = arr[i - 1][j - 1];
				} else if (pattern == '*') {
					arr[i][j] = arr[i - 1][j] || arr[i][j - 1];

				}
			}
		}
		Utils.print(arr);
		return arr[s.length()][p.length()];
	}



	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}




    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        ListNode node1_1 = new ListNode(1);
        ListNode node3 = new ListNode(3);
        ListNode node4_1 = new ListNode(4);

        ListNode node2 = new ListNode(2);
        ListNode node6 = new ListNode(6);

        node1.next = node4;
        node4.next = node5;

        node1_1.next = node3;
        node3.next = node4_1;

        node2.next = node6;

        System.out.println(node1);
        System.out.println(node1_1);
        System.out.println(node2);

//        ListNode node = new MergeKSortedLists().mergeKLists(new ListNode[]{node1, node1_1});
    	LeetCode l = new LeetCode();
        l.mergeKLists(new ListNode[] {node1,node1_1,node2});
    }
    
    ListNode node, head;
    public ListNode mergeKLists(ListNode[] lists) {
       int i=0;
        ListNode poll;
        while(1==1){
            ++i;
           poll = pollSmallest(lists);
            if(poll == null) return head;;
            
            System.out.println(poll.val);
//    printList(lists);
            if(head == null) {
                head = poll;
                node = poll;   
            }
            else node.next = poll;
            
            if(node != null)
                node = node.next;
        }
//        return head;
    }
    ListNode pollSmallest(ListNode[] lists) {
        int small = -1;
        
        for(int i= 0 ; i < lists.length; i++){
            if(lists[i] == null) 
                continue;
            
            if(small == -1 ){
                small = i;
                continue;
            }
            
            if(lists[small].val > lists[i].val)    
                small = i;
        }
        
        if(small == -1) return null;
        
        ListNode temp = lists[small] ;
        lists[small] = lists[small].next;
        temp.next=null;
        return temp;
        
    }
    void printList(ListNode[] lists){
        ListNode n;
         for(int i= 0 ; i < lists.length; i++){
             if(lists[i]==null)continue;
              n = lists[i];
             String  b ="";
             while(n != null){
                 b+=n.val;
                 b+=" ";
                 n=n.next;
                 
             }
             System.out.println(b);
         }
    }
}

	
	
	
	
	
