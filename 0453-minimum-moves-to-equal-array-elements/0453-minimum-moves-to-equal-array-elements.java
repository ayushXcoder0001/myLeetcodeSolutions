class Solution {
    public int minMoves(int[] nums) {
        int min = nums[0];
        int sum = 0;

        for (int num : nums) {
            sum += num;
            if (num < min) {
                min = num;
            }
        }

        return sum - (nums.length * min);
    }
}