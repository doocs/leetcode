class Solution {
    public int numWays(int n, int k) {
        int[][] dp = new int[n][2];
        dp[0][0] = k;
        for (int i = 1; i < n; ++i) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1]) * (k - 1);
            dp[i][1] = dp[i - 1][0];
        }
        return dp[n - 1][0] + dp[n - 1][1];
    }
}