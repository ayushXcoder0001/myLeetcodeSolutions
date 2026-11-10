class Solution {
    public int climbStairs(int n, int[] costs) {
        int[] dp = new int[n + 1];
        java.util.Arrays.fill(dp, Integer.MAX_VALUE);
        
        dp[0] = 0;
        
        for (int i = 0; i <= n; i++) {
            if (dp[i] == Integer.MAX_VALUE) continue;
            
            for (int jump = 1; jump <= 3; jump++) {
                int j = i + jump;
                if (j <= n) {
                    int cost = dp[i] + costs[j - 1] + (j - i) * (j - i);
                    dp[j] = Math.min(dp[j], cost);
                }
            }
        }
        
        return dp[n];
    }
}