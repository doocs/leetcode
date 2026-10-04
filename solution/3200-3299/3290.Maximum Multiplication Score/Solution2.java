class Solution {
    public long maxScore(int[] a, int[] b) {
        int m = a.length, n = b.length;
        long[][] f = new long[m + 1][n + 1];
        for (int i = 0; i < m; ++i) {
            f[i][n] = Long.MIN_VALUE / 2;
        }
        for (int j = n - 1; j >= 0; --j) {
            for (int i = m - 1; i >= 0; --i) {
                f[i][j] = Math.max(f[i][j + 1], 1L * a[i] * b[j] + f[i + 1][j + 1]);
            }
        }
        return f[0][0];
    }
}
