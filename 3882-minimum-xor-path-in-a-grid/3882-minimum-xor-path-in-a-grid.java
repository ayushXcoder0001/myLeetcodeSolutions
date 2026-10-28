class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        java.util.Set<Integer>[][] dp = new java.util.HashSet[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = new java.util.HashSet<>();
            }
        }
        
        dp[0][0].add(grid[0][0]);
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int xorVal : dp[i][j]) {
                    if (j + 1 < n) {
                        dp[i][j + 1].add(xorVal ^ grid[i][j + 1]);
                    }
                    if (i + 1 < m) {
                        dp[i + 1][j].add(xorVal ^ grid[i + 1][j]);
                    }
                }
            }
        }
        
        int minXor = Integer.MAX_VALUE;
        for (int xorVal : dp[m - 1][n - 1]) {
            minXor = Math.min(minXor, xorVal);
        }
        
        return minXor;
    }
}