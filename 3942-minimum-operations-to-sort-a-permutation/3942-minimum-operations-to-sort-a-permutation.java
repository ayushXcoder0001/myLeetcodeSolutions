class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        int idx0 = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                idx0 = i;
                break;
            }
        }

        boolean isIncreasing = true;
        for (int i = 0; i < n; i++) {
            if (nums[(idx0 + i) % n] != i) {
                isIncreasing = false;
                break;
            }
        }

        if (isIncreasing) {
            if (idx0 == 0) return 0;
            return Math.min(idx0, 2 + (n - idx0));
        }

        boolean isDecreasing = true;
        for (int i = 0; i < n; i++) {
            if (nums[(idx0 - i + n) % n] != i) {
                isDecreasing = false;
                break;
            }
        }

        if (isDecreasing) {
            return Math.min(n - idx0, idx0 + 2);
        }

        return -1;
    }
}
