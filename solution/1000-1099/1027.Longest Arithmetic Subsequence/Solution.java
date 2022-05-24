class Solution {
    public int longestArithSeqLength(int[] nums) {
        int n = nums.length;
        int ans = 0;
        int[][] dp = new int[n][1001];
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                int d = nums[i] - nums[j] + 500;
                dp[i][d] = Math.max(dp[i][d], dp[j][d] + 1);
                ans = Math.max(ans, dp[i][d]);
            }
        }
        return ans + 1;
    }
}