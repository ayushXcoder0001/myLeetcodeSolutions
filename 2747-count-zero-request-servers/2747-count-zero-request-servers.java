import java.util.Arrays;
import java.util.HashMap;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        int[][] sortedQueries = new int[m][2];
        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }

        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));
        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));

        int[] ans = new int[m];
        HashMap<Integer, Integer> serverCounts = new HashMap<>();
        int left = 0, right = 0;
        int numLogs = logs.length;

        for (int i = 0; i < m; i++) {
            int qTime = sortedQueries[i][0];
            int qIndex = sortedQueries[i][1];
            int windowStart = qTime - x;
            int windowEnd = qTime;

            while (right < numLogs && logs[right][1] <= windowEnd) {
                int serverId = logs[right][0];
                serverCounts.put(serverId, serverCounts.getOrDefault(serverId, 0) + 1);
                right++;
            }

            while (left < numLogs && logs[left][1] < windowStart) {
                int serverId = logs[left][0];
                serverCounts.put(serverId, serverCounts.get(serverId) - 1);
                if (serverCounts.get(serverId) == 0) {
                    serverCounts.remove(serverId);
                }
                left++;
            }

            ans[qIndex] = n - serverCounts.size();
        }

        return ans;
    }
}