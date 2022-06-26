class Solution {
    public int distinctSequences(int n) {
        if (n == 1) {
            return 6;
        }
        int mod = (int) 1e9 + 7;
        int[][][] dp = new int[n + 1][6][6];
        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < 6; ++j) {
                if (gcd(i + 1, j + 1) == 1 && i != j) {
                    dp[2][i][j] = 1;
                }
            }
        }
        for (int k = 3; k <= n; ++k) {
            for (int i = 0; i < 6; ++i) {
                for (int j = 0; j < 6; ++j) {
                    if (gcd(i + 1, j + 1) == 1 && i != j) {
                        for (int h = 0; h < 6; ++h) {
                            if (gcd(h + 1, i + 1) == 1 && h != i && h != j) {
                                dp[k][i][j] = (dp[k][i][j] + dp[k - 1][h][i]) % mod;
                            }
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < 6; ++j) {
                ans = (ans + dp[n][i][j]) % mod;
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}