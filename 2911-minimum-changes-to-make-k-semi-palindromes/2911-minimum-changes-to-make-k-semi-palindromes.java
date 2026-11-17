class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int[][] cost = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                cost[i][j] = getMinCost(s, i, j);
            }
        }
        
        int[][] dp = new int[n + 1][k + 1];
        int INF = 1000000;
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][0] = 0;
        
        for (int j = 1; j <= k; j++) {
            for (int i = 2 * j; i <= n; i++) {
                for (int prev = 2 * (j - 1); prev <= i - 2; prev++) {
                    if (dp[prev][j - 1] != INF) {
                        dp[i][j] = Math.min(dp[i][j], dp[prev][j - 1] + cost[prev][i - 1]);
                    }
                }
            }
        }
        
        return dp[n][k];
    }
    
    private int getMinCost(String s, int start, int end) {
        int len = end - start + 1;
        int minChanges = len;
        
        for (int d = 1; d * d <= len; d++) {
            if (len % d == 0) {
                minChanges = Math.min(minChanges, calculateChanges(s, start, end, d));
                if (d != 1 && d * d != len) {
                    minChanges = Math.min(minChanges, calculateChanges(s, start, end, len / d));
                }
            }
        }
        
        return minChanges;
    }
    
    private int calculateChanges(String s, int start, int end, int d) {
        int changes = 0;
        for (int rem = 0; rem < d; rem++) {
            int left = start + rem;
            int right = end - (d - 1 - rem);
            while (left < right) {
                if (s.charAt(left) != s.charAt(right)) {
                    changes++;
                }
                left += d;
                right -= d;
            }
        }
        return changes;
    }
}