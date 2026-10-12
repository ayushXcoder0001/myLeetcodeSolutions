class Solution {
    public int[] countBits(int n) {
        // Initialize an array of size n + 1
        int[] ans = new int[n + 1];
        
        // Compute set bits for each number from 1 to n
        for (int i = 1; i <= n; i++) {
            // Recurrence relation: ans[i] = ans[i / 2] + (1 if i is odd, 0 if even)
            ans[i] = ans[i >> 1] + (i & 1);
        }
        
        return ans;
    }
}
