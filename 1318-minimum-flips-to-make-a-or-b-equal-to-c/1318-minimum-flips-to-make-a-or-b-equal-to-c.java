class Solution {
    public int minFlips(int a, int b, int c) {
        int ans = 0;
        for (int i = 0; i < 32; ++i) {
            int x = (a >> i) & 1;
            int y = (b >> i) & 1;
            int z = (c >> i) & 1;
            
            if (z == 0) {
                // If c's bit is 0, both a's and b's bits must be 0.
                // Add 1 flip for each bit that is currently 1.
                ans += x + y;
            } else {
                // If c's bit is 1, at least one of a's or b's bit must be 1.
                // If both are 0, we need 1 flip.
                if (x == 0 && y == 0) {
                    ans += 1;
                }
            }
        }
        return ans;
    }
}
