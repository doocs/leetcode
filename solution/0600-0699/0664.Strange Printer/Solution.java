class Solution {
    public int strangePrinter(String s) {
        int n = s.length();
        int[][] f = new int[n + 1][n + 1];
        for (int i = 0; i < n; ++i) {
            f[i][i] = 1;
        }
        for (int i = n - 2; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = 1 + f[i + 1][j];
                for (int k = i + 1; k <= j; ++k) {
                    if (s.charAt(i) == s.charAt(k)) {
                        f[i][j] = Math.min(f[i][j], f[i + 1][k] + f[k + 1][j]);
                    }
                }
            }
        }
        return f[0][n - 1];
    }
}