class Solution {
    public int numOfSubarrays(int[] arr) {
        int MOD = 1000000007;
        long oddCount = 0;
        long evenCount = 1;
        long currentSum = 0;
        long totalSubarrays = 0;

        for (int num : arr) {
            currentSum += num;
            
            if (currentSum % 2 == 1) {
                totalSubarrays = (totalSubarrays + evenCount) % MOD;
                oddCount++;
            } else {
                totalSubarrays = (totalSubarrays + oddCount) % MOD;
                evenCount++;
            }
        }

        return (int) totalSubarrays;
    }
}