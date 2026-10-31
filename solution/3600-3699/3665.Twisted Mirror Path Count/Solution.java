class Solution {
    public int uniquePaths(int[][] grid) {
        final int mod = (int) 1e9 + 7;
        int m = grid.length, n = grid[0].length;
        int[][][] f = new int[m][n][2];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int r = j > 0 ? f[i][j - 1][0] : 0;
                int d = i > 0 ? f[i - 1][j][1] : 0;
                if (i == 0 && j == 0) {
                    d = 1;
                }
                if (grid[i][j] == 1) {
                    f[i][j][0] = d;
                    f[i][j][1] = r;
                } else {
                    int s = (r + d) % mod;
                    f[i][j][0] = f[i][j][1] = s;
                }
            }
        }
        return f[m - 1][n - 1][0];
    }
}
