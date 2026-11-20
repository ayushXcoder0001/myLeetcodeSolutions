import java.util.Arrays;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        
        int maxVal = nums[nums.length - 1] + 2;
        int[] dp = new int[maxVal];
        
        int maxConsecutive = 0;
        
        for (int x : nums) {
            dp[x + 1] = Math.max(dp[x + 1], dp[x] + 1);
            dp[x] = Math.max(dp[x], dp[x - 1] + 1);
            
            maxConsecutive = Math.max(maxConsecutive, Math.max(dp[x], dp[x + 1]));
        }
        
        return maxConsecutive;
    }
}