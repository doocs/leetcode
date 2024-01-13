class Solution {
    public int minFallingPathSum(int[][] grid) {
        int n = grid.length;
        int[][] f = new int[n + 1][n];
        final int inf = 1 << 30;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < n; ++j) {
                int x = inf;
                for (int k = 0; k < n; ++k) {
                    if (k != j) {
                        x = Math.min(x, f[i - 1][k]);
                    }
                }
                f[i][j] = grid[i - 1][j] + (x == inf ? 0 : x);
            }
        }
        int ans = inf;
        for (int x : f[n]) {
            ans = Math.min(ans, x);
        }
        return ans;
    }
}