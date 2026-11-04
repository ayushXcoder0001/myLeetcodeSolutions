import java.util.*;

class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] cost = new long[n - x + 1];
        
        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();
        int leftCount = 0, rightCount = 0;
        long leftSum = 0, rightSum = 0;

        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (leftCount == 0 || num <= left.lastKey()) {
                left.put(num, left.getOrDefault(num, 0) + 1);
                leftSum += num;
                leftCount++;
            } else {
                right.put(num, right.getOrDefault(num, 0) + 1);
                rightSum += num;
                rightCount++;
            }

            if (i >= x) {
                int out = nums[i - x];
                if (left.containsKey(out)) {
                    left.put(out, left.get(out) - 1);
                    if (left.get(out) == 0) left.remove(out);
                    leftSum -= out;
                    leftCount--;
                } else {
                    right.put(out, right.get(out) - 1);
                    if (right.get(out) == 0) right.remove(out);
                    rightSum -= out;
                    rightCount--;
                }
            }

            int targetLeftCount = (x + 1) / 2;
            while (leftCount < targetLeftCount && rightCount > 0) {
                int firstRight = right.firstKey();
                right.put(firstRight, right.get(firstRight) - 1);
                if (right.get(firstRight) == 0) right.remove(firstRight);
                rightSum -= firstRight;
                rightCount--;

                left.put(firstRight, left.getOrDefault(firstRight, 0) + 1);
                leftSum += firstRight;
                leftCount++;
            }
            while (leftCount > targetLeftCount) {
                int lastLeft = left.lastKey();
                left.put(lastLeft, left.get(lastLeft) - 1);
                if (left.get(lastLeft) == 0) left.remove(lastLeft);
                leftSum -= lastLeft;
                leftCount--;

                right.put(lastLeft, right.getOrDefault(lastLeft, 0) + 1);
                rightSum += lastLeft;
                rightCount++;
            }

            if (i >= x - 1) {
                long median = left.lastKey();
                long currentCost = (median * leftCount - leftSum) + (rightSum - median * rightCount);
                cost[i - x + 1] = currentCost;
            }
        }

        long INF = (long) 1e16;
        long[][] dp = new long[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][0] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                if (dp[i][j] == INF) continue;
                dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j]);
                if (j < k && i + x <= n) {
                    dp[i + x][j + 1] = Math.min(dp[i + x][j + 1], dp[i][j] + cost[i]);
                }
            }
        }

        return dp[n][k] >= INF ? -1 : dp[n][k];
    }
}
