class Solution {
    public int countOfArrays(int n, int m, int k) {
        int[][] f = new int[k + 1][2];
        int cnt0 = m / 2;
        int cnt1 = m - cnt0;
        final int mod = (int) 1e9 + 7;
        f[0][1] = 1;
        for (int i = 0; i < n; ++i) {
            int[][] g = new int[k + 1][2];
            for (int j = 0; j <= k; ++j) {
                g[j][0] = (int) (1L * cnt0 * (f[j][1] + (j > 0 ? f[j - 1][0] : 0)) % mod);
                g[j][1] = (int) (1L * cnt1 * (f[j][0] + f[j][1]) % mod);
            }
            f = g;
        }
        return (f[k][0] + f[k][1]) % mod;
    }
}
