public class Solution {
    public int MaxValue(int[][] grid) {
        int m = grid.Length, n = grid[0].Length;
        int[, ] f = new int[m + 1, n + 1];
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                f[i, j] = Math.Max(f[i - 1, j], f[i, j - 1]) + grid[i - 1][j - 1];
            }
        }
        return f[m, n];
    }
}
