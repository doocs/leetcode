class Solution {
    private boolean[][][] vis;
    private char[][] grid;
    private int m;
    private int n;

    public boolean hasValidPath(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        vis = new boolean[m][n][m + n];
        return dfs(0, 0, 0);
    }

    private boolean dfs(int i, int j, int t) {
        if (vis[i][j][t]) {
            return false;
        }
        vis[i][j][t] = true;
        t += grid[i][j] == '(' ? 1 : -1;
        if (t < 0) {
            return false;
        }
        if (i == m - 1 && j == n - 1) {
            return t == 0;
        }
        int[] dirs = {0, 1, 0};
        for (int k = 0; k < 2; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x < m && y < n && dfs(x, y, t)) {
                return true;
            }
        }
        return false;
    }
}