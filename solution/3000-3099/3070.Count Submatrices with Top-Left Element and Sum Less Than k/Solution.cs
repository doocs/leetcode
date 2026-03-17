public class Solution {
    public int CountSubmatrices(int[][] grid, int k) {
        int m = grid.Length, n = grid[0].Length;
        int[,] s = new int[m + 1, n + 1];
        int ans = 0;
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                s[i, j] = s[i - 1, j] + s[i, j - 1] - s[i - 1, j - 1] + grid[i - 1][j - 1];
                if (s[i, j] <= k) ++ans;
            }
        }
        return ans;
    }
}
