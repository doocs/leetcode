public class Solution {
    public int MinimumArea(int[][] grid) {
        int m = grid.Length, n = grid[0].Length;
        int x1 = m, y1 = n;
        int x2 = 0, y2 = 0;

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    x1 = Math.Min(x1, i);
                    y1 = Math.Min(y1, j);
                    x2 = Math.Max(x2, i);
                    y2 = Math.Max(y2, j);
                }
            }
        }

        return (x2 - x1 + 1) * (y2 - y1 + 1);
    }
}
