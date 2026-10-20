import java.util.Arrays;

class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;

            boolean ok = true;
            int preMax = Integer.MIN_VALUE;

            for (int j = 0; j < n; j += k) {
                int cnt = 1;
                int mx = Integer.MIN_VALUE;
                int mn = Integer.MAX_VALUE;

                for (int m = j; m < j + k; m++) {
                    mx = Math.max(mx, nums[m]);
                    mn = Math.min(mn, nums[m]);
                    if (m > j && nums[m] < nums[m - 1]) {
                        cnt++;
                    }
                }

                if (cnt > 2 || mn < preMax) {
                    ok = false;
                    break;
                }
                if (cnt == 2 && nums[j + k - 1] > nums[j]) {
                    ok = false;
                    break;
                }
                preMax = mx;
            }

            if (ok) {
                ans += k;
            }
        }

        return ans;
    }
}
