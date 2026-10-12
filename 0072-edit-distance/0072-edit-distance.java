class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        // dp[i][j] stores the minimum operations to convert word1[0...i-1] to word2[0...j-1]
        int[][] dp = new int[m + 1][n + 1];
        
        // Base cases: converting to/from an empty string
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i; // i deletions
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j; // j insertions
        }
        
        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // If characters match, no new operation is needed
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // Min of (Replace, Delete, Insert) + 1
                    dp[i][j] = Math.min(dp[i - 1][j - 1], 
                               Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }
        
        return dp[m][n];
    }
}
