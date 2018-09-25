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
			if (!isOptimal(grid,dis,n,p))
			continue;
			queue.add(n);
			dis[n.r][n.c] = Math.min(dis[n.r][n.c], Math.max(grid[n.r][n.c], dis[p.r][p.c]));
		}

		while (!queue.isEmpty())
			swim(queue.poll(), grid, dis, marked, queue);

	}

	boolean isOptimal(int[][] grid, int[][] dis, Point end, Point start) {
		Point p = start, n = end;
		if(dis[end.r][end.c] > dis[start.r][start.c] && grid[end.r][end.c]<  dis[start.r][start.c])
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
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LeetCode l = new LeetCode();
//		int c = l.maxCoins(new int[] {3,1,5,8});
//		System.out.println("max "+c);
		
		l.isMatch("adceb","*a*b");

	}

    public boolean isMatch(String s, String p) {
        boolean[][] arr = new boolean[s.length()+1][p.length()+1];
        arr[0][0] = true;
        for(int i=1; i< arr[0].length;i++) {
        	 
        	if(p.charAt(i-1) == '*'){
                arr[0][i]= arr[0][i-1];
                
            }
        }
        
        for(int i= 1 ; i < arr.length;i++){
            for(int j = 1 ; j < arr[0].length ; j ++){   
                char target = s.charAt(i-1);
                char pattern = p.charAt(j-1);
                if(target == pattern || pattern == '?'){
                    arr[i][j]=arr[i-1][j-1];
                }else if(pattern == '*'){
                    arr[i][j]= arr[i-1][j]||arr[i][j-1] ;
                    
                }
            }
        }
        Utils.print(arr);
        return arr[s.length()][p.length()];
    }
    
    
}



