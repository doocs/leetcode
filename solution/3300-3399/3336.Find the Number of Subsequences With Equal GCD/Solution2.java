class Solution {
    public int subsequencePairCount(int[] nums) {
        final int MOD = 1_000_000_007;
        int m = 0;
        for (int x : nums) {
            m = Math.max(m, x);
        }
        int[][] f = new int[m + 1][m + 1];
        f[0][0] = 1;
        for (int x : nums) {
            int[][] g = new int[m + 1][m + 1];
            for (int j = 0; j <= m; ++j) {
                for (int k = 0; k <= m; ++k) {
                    if (f[j][k] == 0) {
                        continue;
                    }
                    int v = f[j][k];
                    g[j][k] = (g[j][k] + v) % MOD;
                    int nj = gcd(x, j);
                    g[nj][k] = (g[nj][k] + v) % MOD;
                    int nk = gcd(x, k);
                    g[j][nk] = (g[j][nk] + v) % MOD;
                }
            }
            f = g;
        }
        long ans = 0;
        for (int i = 0; i <= m; ++i) {
            ans += f[i][i];
        }
        return (int) ((ans - 1 + MOD) % MOD);
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
