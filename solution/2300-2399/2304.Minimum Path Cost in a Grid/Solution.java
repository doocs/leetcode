class Solution {
    public int minPathCost(int[][] grid, int[][] moveCost) {
        int m = grid.length, n = grid[0].length;
        int inf = Integer.MAX_VALUE;
        int[] f = new int[n];
        for (int i = 0; i < m; ++i) {
            int[] g = new int[n];
            for (int j = 0; j < n; ++j) {
                g[j] = grid[i][j];
                int t = inf;
                if (i > 0) {
                    for (int k = 0; k < n; ++k) {
                        t = Math.min(t, f[k] + moveCost[grid[i - 1][k]][j]);
                    }
                }
                if (t != inf) {
                    g[j] += t;
                }
            }
            f = g;
        }
        int ans = inf;
        for (int v : f) {
            ans = Math.min(ans, v);
        }
        return ans;
    }
}