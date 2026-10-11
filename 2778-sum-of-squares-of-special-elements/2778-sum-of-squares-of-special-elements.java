class Solution {
    public int sumOfSquares(int[] nums) {
        int n = nums.length;
        int sumOfSquares = 0;
        
        for (int i = 0; i < n; i++) {
            // Convert 0-indexed 'i' to 1-indexed position
            int position = i + 1;
            
            // Check if the 1-based position divides the array length evenly
            if (n % position == 0) {
                sumOfSquares += nums[i] * nums[i];
            }
        }
        
        return sumOfSquares;
    }
}
