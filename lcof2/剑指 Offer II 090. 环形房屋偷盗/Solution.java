class Solution {
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        return Math.max(_rob(nums, 0, nums.length - 1), _rob(nums, 1, nums.length));
    }

    public int _rob(int[] nums, int start, int end) {
        if (start + 1 == end) {
            return nums[start];
        }
        int n = end - start;
        int[] dp = new int[n];
        dp[0] = nums[start];
        dp[1] = Math.max(nums[start], nums[start + 1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[start + i], dp[i - 1]);
        }
        return dp[n - 1];
    }
}
