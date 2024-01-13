class Solution {
    public int maxValue(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] f = new int[2][n + 1];
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                f[i & 1][j] = Math.max(f[i & 1 ^ 1][j], f[i & 1][j - 1]) + grid[i - 1][j - 1];
            }
        }
        return f[m & 1][n];
    }
}