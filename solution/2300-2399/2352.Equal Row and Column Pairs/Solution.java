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
        for (int[] row : grid) {
            for (int[] col : g) {
                boolean ok = true;
                for (int i = 0; i < n; ++i) {
                    if (row[i] != col[i]) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}