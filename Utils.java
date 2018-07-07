import java.util.*;

public class Utils {

	
	public static void pint(int[]arr){
		System.out.println(Arrays.toString(arr));
		
	

	}
	public static void pint(int[][] arr){
		for (int[] x : arr)
		{
		   for (int y : x)
		   {
		        System.out.print(y + " ");
		   }
		   System.out.println();
		}
	}
}
