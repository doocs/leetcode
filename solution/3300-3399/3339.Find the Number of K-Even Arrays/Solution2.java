class Solution {
    public int countOfArrays(int n, int m, int k) {
        int[][][] f = new int[n + 1][k + 1][2];
        int cnt0 = m / 2;
        int cnt1 = m - cnt0;
        final int mod = (int) 1e9 + 7;
        f[0][0][1] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= k; ++j) {
                f[i][j][0]
                    = (int) (1L * cnt0 * (f[i - 1][j][1] + (j > 0 ? f[i - 1][j - 1][0] : 0)) % mod);
                f[i][j][1] = (int) (1L * cnt1 * (f[i - 1][j][0] + f[i - 1][j][1]) % mod);
            }
        }
        return (f[n][k][0] + f[n][k][1]) % mod;
    }
}
