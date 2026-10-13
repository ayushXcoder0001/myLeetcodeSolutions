import java.util.Arrays;

public class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squares = new long[n];
        
        // Convert all values to their squares
        for (int i = 0; i < n; i++) {
            squares[i] = (long) nums[i] * nums[i];
        }
        
        // Sort the squares in ascending order
        Arrays.sort(squares);
        
        long totalScore = 0;
        int mid = n / 2;
        
        // Subtract the first half (smaller squares) 
        // and add the second half (larger squares)
        for (int i = 0; i < n; i++) {
            if (i < mid) {
                totalScore -= squares[i];
            } else {
                totalScore += squares[i];
            }
        }
        
        return totalScore;
    }
}
