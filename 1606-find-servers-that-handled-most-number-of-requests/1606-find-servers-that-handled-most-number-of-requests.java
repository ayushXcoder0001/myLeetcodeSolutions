import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        
        TreeSet<Integer> availableServers = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            availableServers.add(i);
        }
        
        int[] requestCount = new int[k];
        int maxRequests = 0;
        
        for (int i = 0; i < arrival.length; i++) {
            int currTime = arrival[i];
            int duration = load[i];
            
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= currTime) {
                availableServers.add(busyServers.poll()[1]);
            }
            
            if (availableServers.isEmpty()) {
                continue;
            }
            
            Integer targetServer = availableServers.ceiling(i % k);
            
            if (targetServer == null) {
                targetServer = availableServers.first();
            }
            
            availableServers.remove(targetServer);
            busyServers.offer(new int[] { currTime + duration, targetServer });
            
            requestCount[targetServer]++;
            maxRequests = Math.max(maxRequests, requestCount[targetServer]);
        }
        
        List<Integer> busiest = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requestCount[i] == maxRequests) {
                busiest.add(i);
            }
        }
        
        return busiest;
    }
}