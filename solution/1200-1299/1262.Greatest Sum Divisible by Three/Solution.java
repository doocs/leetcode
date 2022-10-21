class Solution {
    public int maxSumDivThree(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n + 1][3];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][j] = dp[i - 1][j];
            }
            int v = nums[i - 1] % 3;
            if (v == 0) {
                dp[i][0] += nums[i - 1];
                if (dp[i - 1][1] != 0) {
                    dp[i][1] += nums[i - 1];
                }
                if (dp[i - 1][2] != 0) {
                    dp[i][2] += nums[i - 1];
                }
            } else if (v == 1) {
                if (dp[i - 1][2] != 0) {
                    dp[i][0] = Math.max(dp[i][0], dp[i - 1][2] + nums[i - 1]);
                }
                dp[i][1] = Math.max(dp[i][1], dp[i - 1][0] + nums[i - 1]);
                if (dp[i - 1][1] != 0) {
                    dp[i][2] = Math.max(dp[i][2], dp[i - 1][1] + nums[i - 1]);
                }
            } else {
                if (dp[i - 1][1] != 0) {
                    dp[i][0] = Math.max(dp[i][0], dp[i - 1][1] + nums[i - 1]);
                }
                if (dp[i - 1][2] != 0) {
                    dp[i][1] = Math.max(dp[i][1], dp[i - 1][2] + nums[i - 1]);
                }
                dp[i][2] = Math.max(dp[i][2], dp[i - 1][0] + nums[i - 1]);
            }
        }
        return dp[n][0];
    }
}
