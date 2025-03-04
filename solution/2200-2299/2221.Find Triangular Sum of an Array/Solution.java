class Solution {
    public int triangularSum(int[] nums) {
        for (int k = nums.length - 1; k > 0; --k) {
            for (int i = 0; i < k; ++i) {
                nums[i] = (nums[i] + nums[i + 1]) % 10;
            }
        }
        return nums[0];
    }
}
