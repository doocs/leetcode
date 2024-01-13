class Solution {
    public int numSquares(int n) {
        int m = (int) Math.sqrt(n);
        int[][] f = new int[m + 1][n + 1];
        for (var g : f) {
            Arrays.fill(g, 1 << 30);
        }
        f[0][0] = 0;
        for (int i = 1; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                f[i][j] = f[i - 1][j];
                if (j >= i * i) {
                    f[i][j] = Math.min(f[i][j], f[i][j - i * i] + 1);
                }
            }
        }
        return f[m][n];
    }
}