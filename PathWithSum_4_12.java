import java.util.HashMap;

public class PathWithSum_4_12 {

	// Paths with Sum: You are given a binary tree in which each node contains an
	// integer value (which
	// might be positive or negative). Design an algorithm to count the number of
	// paths that sum to a
	// given value. The path does not need to start or end at the root or a leaf,
	// but it must go downwards
	// (traveling only from parent nodes to child nodes).
	// Hints:#6, #14, #52, #68, #77, #87, #94, #103, #108, #115



	class Node {
		Node left, right;
		int value;
	}
	
	
	  // Returns true if arr[] has a subarray with sero sum
    static Boolean subArrayExists(int arr[])
    {
        // Creates an empty hashMap hM
        HashMap<Integer, Integer> hM = 
                        new HashMap<Integer, Integer>();
         
        // Initialize sum of elements
        int sum = 0;     
         
        // Traverse through the given array
        for (int i = 0; i < arr.length; i++)
        { 
            // Add current element to sum
            sum += arr[i];
             
            // Return true in following cases
            // a) Current element is 0
            // b) sum of elements from 0 to i is 0
            // c) sum is already present in hash map
            if (arr[i] == 0 || sum == 0 || hM.get(sum) != null)                         
                return true;
             
            // Add sum to hash map
            hM.put(sum, i);
        } 
         
        // We reach here only when there is
        // no subarray with 0 sum
        return false;
    }     
     
    // driver code
    public static void main(String arg[])
    {
        int arr[] = {-3, 2, 3, 1, 6};
        if (subArrayExists(arr))
            System.out.println("Found a subarray with 0 sum");
        else
            System.out.println("No Such Sub Array Exists!");         
    }     

}
