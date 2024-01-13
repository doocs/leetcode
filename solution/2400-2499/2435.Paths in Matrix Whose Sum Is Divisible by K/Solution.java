class Solution {
    private int m;
    private int n;
    private int k;
    private static final int MOD = (int) 1e9 + 7;
    private int[][] grid;
    private int[][][] f;

    public int numberOfPaths(int[][] grid, int k) {
        this.grid = grid;
        this.k = k;
        m = grid.length;
        n = grid[0].length;
        f = new int[m][n][k];
        for (var a : f) {
            for (var b : a) {
                Arrays.fill(b, -1);
            }
        }
        return dfs(0, 0, 0);
    }

    private int dfs(int i, int j, int s) {
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return 0;
        }
        s = (s + grid[i][j]) % k;
        if (f[i][j][s] != -1) {
            return f[i][j][s];
        }
        if (i == m - 1 && j == n - 1) {
            return s == 0 ? 1 : 0;
        }
        int ans = dfs(i + 1, j, s) + dfs(i, j + 1, s);
        ans %= MOD;
        f[i][j][s] = ans;
        return ans;
    }
}