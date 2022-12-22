class Solution {
    private int s;
    private int m;
    private int n;
    private int[][] grid;
    private Boolean[][][] f;

    public boolean isThereAPath(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        s = m + n - 1;
        f = new Boolean[m][n][s];
        if (s % 2 == 1) {
            return false;
        }
        s >>= 1;
        return dfs(0, 0, 0);
    }

    private boolean dfs(int i, int j, int k) {
        if (i >= m || j >= n) {
            return false;
        }
        k += grid[i][j];
        if (f[i][j][k] != null) {
            return f[i][j][k];
        }
        if (k > s || i + j + 1 - k > s) {
            return false;
        }
        if (i == m - 1 && j == n - 1) {
            return k == s;
        }
        f[i][j][k] = dfs(i + 1, j, k) || dfs(i, j + 1, k);
        return f[i][j][k];
    }
}