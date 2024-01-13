public class Solution {
    private int m;
    private int n;
    private int[][] grid;

    public int ClosedIsland(int[][] grid) {
        m = grid.Length;
        n = grid[0].Length;
        this.grid = grid;
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    ans += dfs(i, j);
                }
            }
        }
        return ans;
    }

    private int dfs(int i, int j) {
        int res = i > 0 && i < m - 1 && j > 0 && j < n - 1 ? 1 : 0;
        grid[i][j] = 1;
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 0) {
                res &= dfs(x, y);
            }
        }
        return res;
    }
}
