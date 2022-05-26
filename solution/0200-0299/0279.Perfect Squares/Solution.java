class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            int mi = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; ++j) {
                mi = Math.min(mi, dp[i - j * j]);
            }
            dp[i] = mi + 1;
        }
        return dp[n];
    }
}