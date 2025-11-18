class Solution {
    public int numberOfPaths(int[][] grid, int K) {
        final int mod = (int) 1e9 + 7;
        int m = grid.length, n = grid[0].length;
        int[][][] f = new int[m][n][K];
        f[0][0][grid[0][0] % K] = 1;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < K; ++k) {
                    int k0 = ((k - grid[i][j] % K) + K) % K;
                    if (i > 0) {
                        f[i][j][k] = (f[i][j][k] + f[i - 1][j][k0]) % mod;
                    }
                    if (j > 0) {
                        f[i][j][k] = (f[i][j][k] + f[i][j - 1][k0]) % mod;
                    }
                }
            }
        }
        return f[m - 1][n - 1][0];
    }
}
