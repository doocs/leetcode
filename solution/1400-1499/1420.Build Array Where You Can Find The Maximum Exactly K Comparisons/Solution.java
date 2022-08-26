class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int numOfArrays(int n, int m, int k) {
        if (k == 0) {
            return 0;
        }
        long[][][] dp = new long[n + 1][k + 1][m + 1];
        for (int i = 1; i <= m; ++i) {
            dp[1][1][i] = 1;
        }
        for (int i = 2; i <= n; ++i) {
            for (int c = 1; c <= Math.min(i, k); ++c) {
                for (int j = 1; j <= m; ++j) {
                    dp[i][c][j] = (dp[i - 1][c][j] * j) % MOD;
                    for (int j0 = 1; j0 < j; ++j0) {
                        dp[i][c][j] = (dp[i][c][j] + dp[i - 1][c - 1][j0]) % MOD;
                    }
                }
            }
        }
        long ans = 0;
        for (int i = 1; i <= m; ++i) {
            ans = (ans + dp[n][k][i]) % MOD;
        }
        return (int) ans;
    }
}