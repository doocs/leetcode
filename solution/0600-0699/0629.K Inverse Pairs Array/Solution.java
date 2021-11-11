class Solution {

    private static final int MOD = 1000000007;

    public int kInversePairs(int n, int k) {
        int[] dp = new int[k + 1];
        int[] pre = new int[k + 2];
        for (int i = 1; i <= n; i++) {
            dp[0] = 1;

            // dp[i][j] = dp[i - 1][j - (i - 1)] + ... + dp[i - 1][j]
            for (int j = 1; j <= k; j++) {
                dp[j] = (pre[j + 1] - pre[Math.max(0, j - i + 1)] + MOD) % MOD;
            }

            for (int j = 1; j <= k + 1; j++) {
                pre[j] = (pre[j - 1] + dp[j - 1]) % MOD;
            }
        }
        return dp[k];
    }
}
