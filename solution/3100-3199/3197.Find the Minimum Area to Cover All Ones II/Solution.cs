public class Solution {
    private const int INF = 1 << 30;
    private int[][] grid;

    public int MinimumSum(int[][] grid) {
        this.grid = grid;
        int m = grid.Length;
        int n = grid[0].Length;
        int ans = m * n;

        for (int i1 = 0; i1 < m - 1; i1++) {
            for (int i2 = i1 + 1; i2 < m - 1; i2++) {
                ans = Math.Min(
                    ans, F(0, 0, i1, n - 1) + F(i1 + 1, 0, i2, n - 1) + F(i2 + 1, 0, m - 1, n - 1));
            }
        }

        for (int j1 = 0; j1 < n - 1; j1++) {
            for (int j2 = j1 + 1; j2 < n - 1; j2++) {
                ans = Math.Min(
                    ans, F(0, 0, m - 1, j1) + F(0, j1 + 1, m - 1, j2) + F(0, j2 + 1, m - 1, n - 1));
            }
        }

        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                ans = Math.Min(
                    ans, F(0, 0, i, j) + F(0, j + 1, i, n - 1) + F(i + 1, 0, m - 1, n - 1));
                ans = Math.Min(
                    ans, F(0, 0, i, n - 1) + F(i + 1, 0, m - 1, j) + F(i + 1, j + 1, m - 1, n - 1));

                ans = Math.Min(
                    ans, F(0, 0, i, j) + F(i + 1, 0, m - 1, j) + F(0, j + 1, m - 1, n - 1));
                ans = Math.Min(
                    ans, F(0, 0, m - 1, j) + F(0, j + 1, i, n - 1) + F(i + 1, j + 1, m - 1, n - 1));
            }
        }
        return ans;
    }

    private int F(int i1, int j1, int i2, int j2) {
        int x1 = INF, y1 = INF;
        int x2 = -INF, y2 = -INF;
        for (int i = i1; i <= i2; i++) {
            for (int j = j1; j <= j2; j++) {
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
