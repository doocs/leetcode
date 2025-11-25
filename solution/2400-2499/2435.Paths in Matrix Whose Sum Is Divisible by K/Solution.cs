public class Solution {
    public int NumberOfPaths(int[][] grid, int k) {
        const int mod = (int) 1e9 + 7;
        int m = grid.Length, n = grid[0].Length;
        int[,,] f = new int[m, n, k];
        f[0, 0, grid[0][0] % k] = 1;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int p = 0; p < k; ++p) {
                    int k0 = ((p - grid[i][j] % k) + k) % k;
                    if (i > 0) {
                        f[i, j, p] = (f[i, j, p] + f[i - 1, j, k0]) % mod;
                    }
                    if (j > 0) {
                        f[i, j, p] = (f[i, j, p] + f[i, j - 1, k0]) % mod;
                    }
                }
            }
        }
        return f[m - 1, n - 1, 0];
    }
}
