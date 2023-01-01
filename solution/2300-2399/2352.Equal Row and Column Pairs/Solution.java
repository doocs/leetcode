class Solution {
    public int equalPairs(int[][] grid) {
        int n = grid.length;
        int[][] g = new int[n][n];
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < n; ++i) {
                g[i][j] = grid[j][i];
            }
        }
        int ans = 0;
        for (var row : grid) {
            for (var col : g) {
                int ok = 1;
                for (int i = 0; i < n; ++i) {
                    if (row[i] != col[i]) {
                        ok = 0;
                        break;
                    }
                }
                ans += ok;
            }
        }
        return ans;
    }
}