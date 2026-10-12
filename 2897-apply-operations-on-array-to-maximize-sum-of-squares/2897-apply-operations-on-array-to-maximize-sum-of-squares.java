import java.util.List;

public class Solution {
    public int maxSum(List<Integer> nums, int k) {
        // Since nums[i] <= 10^9, we only need to track up to 30 bits (2^30 > 10^9)
        int[] bitCounts = new int[30];
        
        // Step 1: Count the occurrences of each bit across all numbers
        for (int num : nums) {
            for (int i = 0; i < 30; i++) {
                if (((num >> i) & 1) == 1) {
                    bitCounts[i]++;
                }
            }
        }
        
        long totalSumOfSquares = 0;
        long modulo = 1_000_000_007;
        
        // Step 2: Greedily construct the k largest possible numbers
        for (int i = 0; i < k; i++) {
            long currentNumber = 0;
            for (int bit = 0; bit < 30; bit++) {
                if (bitCounts[bit] > 0) {
                    currentNumber |= (1L << bit);
                    bitCounts[bit]--; // Consume the bit
                }
            }
            // Step 3: Add the squared value of the constructed number to the total sum
            long squaredValue = (currentNumber * currentNumber) % modulo;
            totalSumOfSquares = (totalSumOfSquares + squaredValue) % modulo;
        }
        
        return (int) totalSumOfSquares;
    }
}
