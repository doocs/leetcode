class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int idealArrays(int n, int maxValue) {
        int[][] c = new int[n][16];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= i && j < 16; ++j) {
                c[i][j] = j == 0 ? 1 : (c[i - 1][j] + c[i - 1][j - 1]) % MOD;
            }
        }
        long[][] dp = new long[maxValue + 1][16];
        for (int i = 1; i <= maxValue; ++i) {
            dp[i][1] = 1;
        }
        for (int j = 1; j < 15; ++j) {
            for (int i = 1; i <= maxValue; ++i) {
                int k = 2;
                for (; k * i <= maxValue; ++k) {
                    dp[k * i][j + 1] = (dp[k * i][j + 1] + dp[i][j]) % MOD;
                }
            }
        }
        long ans = 0;
        for (int i = 1; i <= maxValue; ++i) {
            for (int j = 1; j < 16; ++j) {
                ans = (ans + dp[i][j] * c[n - 1][j - 1]) % MOD;
            }
        }
        return (int) ans;
    }
}