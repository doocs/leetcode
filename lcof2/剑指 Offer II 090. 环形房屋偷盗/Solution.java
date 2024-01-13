class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        return Math.max(rob(nums, 0, n - 2), rob(nums, 1, n - 1));
    }

    private int rob(int[] nums, int l, int r) {
        int f = 0, g = 0;
        for (; l <= r; ++l) {
            int ff = Math.max(f, g);
            g = f + nums[l];
            f = ff;
        }
        return Math.max(f, g);
    }
}