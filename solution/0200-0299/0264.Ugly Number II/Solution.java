class Solution {
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int i = 0, j = 0, k = 0;
        for (int idx = 1; idx < n; ++idx) {
            int t = Math.min(dp[i] * 2, Math.min(dp[j] * 3, dp[k] * 5));
            dp[idx] = t;
            if (dp[i] * 2 == t) ++i;
            if (dp[j] * 3 == t) ++j;
            if (dp[k] * 5 == t) ++k;
        }
        return dp[n - 1];
    }
}
