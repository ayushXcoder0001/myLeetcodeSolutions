import java.util.*;

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        Integer[] indices = new Integer[n];
        int[] digitSums = new int[n];
        
        for (int i = 0; i < n; i++) {
            indices[i] = i;
            digitSums[i] = getDigitSum(nums[i]);
        }
        
        Arrays.sort(indices, (a, b) -> {
            if (digitSums[a] != digitSums[b]) {
                return Integer.compare(digitSums[a], digitSums[b]);
            }
            return Integer.compare(nums[a], nums[b]);
        });
        
        boolean[] visited = new boolean[n];
        int swaps = 0;
        
        for (int i = 0; i < n; i++) {
            if (visited[i] || indices[i] == i) {
                continue;
            }
            
            int cycleSize = 0;
            int j = i;
            
            while (!visited[j]) {
                visited[j] = true;
                j = indices[j];
                cycleSize++;
            }
            
            if (cycleSize > 1) {
                swaps += (cycleSize - 1);
            }
        }
        
        return swaps;
    }
    
    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
