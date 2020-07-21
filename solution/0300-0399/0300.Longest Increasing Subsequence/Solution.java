class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        int res = 1;
        for (int i = 1; i < n; ++i) {
            int maxVal = 0;
            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i]) {
                    maxVal = Math.max(maxVal, dp[j]);
                }
            }
            dp[i] = maxVal + 1;
            res = Math.max(res, dp[i]);
        }
        return res;

    }
}