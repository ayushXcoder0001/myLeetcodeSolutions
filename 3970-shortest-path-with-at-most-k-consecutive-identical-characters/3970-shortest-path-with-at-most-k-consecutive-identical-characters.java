import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            graph[u].add(new int[]{v, w});
        }

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));

        long[][] minCost = new long[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(minCost[i], Long.MAX_VALUE);
        }

        pq.offer(new long[]{0, 0, 1});
        minCost[0][1] = 0;

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long d = curr[0];
            int u = (int) curr[1];
            int count = (int) curr[2];

            if (u == n - 1) {
                return (int) d;
            }

            if (d > minCost[u][count]) {
                continue;
            }

            for (int[] neighbor : graph[u]) {
                int v = neighbor[0];
                int w = neighbor[1];

                int nextCount;
                if (labels.charAt(u) == labels.charAt(v)) {
                    nextCount = count + 1;
                } else {
                    nextCount = 1;
                }

                if (nextCount <= k) {
                    if (d + w < minCost[v][nextCount]) {
                        minCost[v][nextCount] = d + w;
                        pq.offer(new long[]{d + w, v, nextCount});
                    }
                }
            }
        }

        long ans = Long.MAX_VALUE;
        for (int count = 1; count <= k; count++) {
            ans = Math.min(ans, minCost[n - 1][count]);
        }

        return ans == Long.MAX_VALUE ? -1 : (int) ans;
    }
}