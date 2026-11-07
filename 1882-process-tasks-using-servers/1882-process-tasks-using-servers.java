import java.util.PriorityQueue;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];

        PriorityQueue<int[]> freeServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[2], b[2]);
        });

        for (int i = 0; i < n; i++) {
            freeServers.offer(new int[] { servers[i], i });
        }

        int time = 0;
        for (int j = 0; j < m; j++) {
            time = Math.max(time, j);

            if (freeServers.isEmpty()) {
                time = Math.max(time, busyServers.peek()[0]);
            }

            while (!busyServers.isEmpty() && busyServers.peek()[0] <= time) {
                int[] busy = busyServers.poll();
                freeServers.offer(new int[] { busy[1], busy[2] });
            }

            int[] server = freeServers.poll();
            int weight = server[0];
            int index = server[1];

            ans[j] = index;
            busyServers.offer(new int[] { time + tasks[j], weight, index });
        }

        return ans;
    }
}