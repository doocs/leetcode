class Solution {
    public int pivotIndex(int[] nums) {
        int sums = 0;
        for (int e : nums) {
            sums += e;
        }
        int preSum = 0;
        for (int i = 0; i < nums.length; ++i) {
            // preSum == sums - nums[i] - preSum
            if (preSum << 1 == sums - nums[i]) {
                return i;
            }
            preSum += nums[i];
        }
        return -1;
    }
}