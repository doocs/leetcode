class Solution {
    private int[][] grid;
    private Integer[][][] f;
    private final int inf = 1 << 30;

    public int maxPathScore(int[][] grid, int k) {
        this.grid = grid;
        int m = grid.length;
        int n = grid[0].length;
        f = new Integer[m][n][k + 1];
        int ans = dfs(m - 1, n - 1, k);
        return ans < 0 ? -1 : ans;
    }

    private int dfs(int i, int j, int k) {
        if (i < 0 || j < 0 || k < 0) {
            return -inf;
        }
        if (i == 0 && j == 0) {
            return 0;
        }
        if (f[i][j][k] != null) {
            return f[i][j][k];
        }
        int res = grid[i][j];
        int nk = k;
        if (grid[i][j] > 0) {
            --nk;
        }
        int a = dfs(i - 1, j, nk);
        int b = dfs(i, j - 1, nk);
        res += Math.max(a, b);
        f[i][j][k] = res;
        return res;
    }
}
