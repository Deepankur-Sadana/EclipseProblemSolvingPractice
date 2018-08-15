
import java.util.*;

public class ModerateFinals {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ModerateFinals m = new ModerateFinals();
		m.getPondSizes(m.generatePond());

	}

	int[][] generatePond() {
		return new int[][] {

				{ 0, 2, 1, 0 ,0},
				{ 0, 1, 0, 1 ,0},
				{ 1, 1, 0, 1 ,0}, 
				{ 0, 0, 0, 0 ,0} 
				};
	}
//	16.19 Pond Sizes: You

	void getPondSizes(int[][] area) {

		ArrayList<Integer> ponds = new ArrayList<>();

		boolean[][] marked = new boolean[area.length][area[0].length];
		Utils.pint(area);
		for (int i = 0; i < area.length; i++) {
			for (int j = 0; j < area[0].length; j++) {
				int size = traverse(0, marked, new Point(i, j), area);
				if (size > 0)
					ponds.add(size);
			}
		}
		for (int i : ponds)
			System.out.println(" .... " + i);
	}

	int traverse(int currentPondSize, boolean[][] marked, Point p, int[][] area) {

		if (!isAValidPoint(p, area))
			return 0;

		if (marked[p.r][p.c])
			return 0;

		if (area[p.r][p.c] > 0)
			return 0;

		marked[p.r][p.c] = true;

		// return +size traversed in all directions

		Point[] points = getAllNeighbours(p);
		int s = currentPondSize+1;
		for (Point cp : points)
			s += traverse(currentPondSize , marked, cp, area);
		return s;

	}

	Point[] getAllNeighbours(Point p) {
		return new Point[] { new Point(p.r - 1, p.c), new Point(p.r - 1, p.c - 1), new Point(p.r - 1, p.c),
				new Point(p.r - 1, p.c + 1), new Point(p.r, p.c - 1), new Point(p.r, p.c + 1),
				new Point(p.r + 1, p.c - 1), new Point(p.r + 1, p.c), new Point(p.r + 1, p.c + 1) };
	}

	boolean isAValidPoint(Point p, int[][] area) {
		return p.r >= 0 && p.c >= 0 && p.r < area.length && p.c < area[0].length;

	}

	static class Point {
		int r, c;

		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
