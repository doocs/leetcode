class Solution {
    public int countPyramids(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] f = new int[m][n];
        int ans = 0;
        for (int i = m - 1; i >= 0; --i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    f[i][j] = -1;
                } else if (i == m - 1 || j == 0 || j == n - 1) {
                    f[i][j] = 0;
                } else {
                    f[i][j] = Math.min(f[i + 1][j - 1], Math.min(f[i + 1][j], f[i + 1][j + 1])) + 1;
                    ans += f[i][j];
                }
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    f[i][j] = -1;
                } else if (i == 0 || j == 0 || j == n - 1) {
                    f[i][j] = 0;
                } else {
                    f[i][j] = Math.min(f[i - 1][j - 1], Math.min(f[i - 1][j], f[i - 1][j + 1])) + 1;
                    ans += f[i][j];
                }
            }
        }
        return ans;
    }
}