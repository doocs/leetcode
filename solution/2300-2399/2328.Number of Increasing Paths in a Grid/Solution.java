class Solution {
    private int m;
    private int n;
    private int[][] g;
    private int[][] f;
    private static final int MOD = (int) 1e9 + 7;

    public int countPaths(int[][] grid) {
        g = grid;
        m = g.length;
        n = g[0].length;
        f = new int[m][n];
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans = (ans + dfs(i, j)) % MOD;
            }
        }
        return ans;
    }

    private int dfs(int i, int j) {
        if (f[i][j] != 0) {
            return f[i][j];
        }
        int res = 1;
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && g[x][y] > g[i][j]) {
                res = (res + dfs(x, y)) % MOD;
            }
        }
        f[i][j] = res;
        return res;
    }
}