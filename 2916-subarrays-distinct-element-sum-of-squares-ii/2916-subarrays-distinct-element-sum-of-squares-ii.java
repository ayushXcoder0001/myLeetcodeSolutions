import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final int MOD = 1_000_000_007;

    private static class SegmentTree {
        int n;
        long[] sum;
        long[] sqSum;
        long[] lazy;

        SegmentTree(int n) {
            this.n = n;
            this.sum = new long[4 * n];
            this.sqSum = new long[4 * n];
            this.lazy = new long[4 * n];
        }

        private void pushDown(int node, int start, int end) {
            if (lazy[node] == 0) return;

            long val = lazy[node];
            int mid = start + (end - start) / 2;
            int leftChild = 2 * node;
            int rightChild = 2 * node + 1;

            updateNode(leftChild, start, mid, val);
            updateNode(rightChild, mid + 1, end, val);

            lazy[node] = 0;
        }

        private void updateNode(int node, int start, int end, long val) {
            long rangeLen = end - start + 1;
            
            long newSqSum = (sqSum[node] + 2 * val % MOD * sum[node] % MOD + rangeLen * val % MOD * val % MOD) % MOD;
            long newSum = (sum[node] + rangeLen * val % MOD) % MOD;

            sqSum[node] = newSqSum;
            sum[node] = newSum;
            lazy[node] = (lazy[node] + val) % MOD;
        }

        public void update(int node, int start, int end, int l, int r, long val) {
            if (r < start || end < l) {
                return;
            }
            if (l <= start && end <= r) {
                updateNode(node, start, end, val);
                return;
            }

            pushDown(node, start, end);
            int mid = start + (end - start) / 2;
            update(2 * node, start, mid, l, r, val);
            update(2 * node + 1, mid + 1, end, l, r, val);

            sum[node] = (sum[2 * node] + sum[2 * node + 1]) % MOD;
            sqSum[node] = (sqSum[2 * node] + sqSum[2 * node + 1]) % MOD;
        }
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        SegmentTree tree = new SegmentTree(n);
        Map<Integer, Integer> lastSeen = new HashMap<>();
        long totalSum = 0;

        for (int r = 0; r < n; r++) {
            int l = lastSeen.containsKey(nums[r]) ? lastSeen.get(nums[r]) + 1 : 0;
            
            tree.update(1, 0, n - 1, l, r, 1);
            
            totalSum = (totalSum + tree.sqSum[1]) % MOD;
            
            lastSeen.put(nums[r], r);
        }

        return (int) totalSum;
    }
}
