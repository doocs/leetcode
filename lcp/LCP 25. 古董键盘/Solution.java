class Solution {
    public int keyboard(int k, int n) {
        int[][] c = new int[n + 1][k + 1];
        for (int i = 0; i <= n; ++i) {
            c[i][0] = 1;
        }
        final int mod = (int) 1e9 + 7;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= k; ++j) {
                c[i][j] = (c[i - 1][j - 1] + c[i - 1][j]) % mod;
            }
        }
        int[][] f = new int[n + 1][27];
        Arrays.fill(f[0], 1);
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j < 27; ++j) {
                for (int h = 0; h <= Math.min(i, k); ++h) {
                    f[i][j] = (f[i][j] + (int) ((long) f[i - h][j - 1] * c[i][h] % mod)) % mod;
                }
            }
        }
        return f[n][26];
    }
}
