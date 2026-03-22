class Solution {
    public int maxProductPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long[][][] f = new long[m][n][2];

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                long x = grid[i][j];

                if (i == 0 && j == 0) {
                    f[i][j][0] = x;
                    f[i][j][1] = x;
                    continue;
                }

                long mn = Long.MAX_VALUE, mx = Long.MIN_VALUE;

                if (i > 0) {
                    long a = f[i - 1][j][0], b = f[i - 1][j][1];
                    mn = Math.min(mn, Math.min(a * x, b * x));
                    mx = Math.max(mx, Math.max(a * x, b * x));
                }

                if (j > 0) {
                    long a = f[i][j - 1][0], b = f[i][j - 1][1];
                    mn = Math.min(mn, Math.min(a * x, b * x));
                    mx = Math.max(mx, Math.max(a * x, b * x));
                }

                f[i][j][0] = mn;
                f[i][j][1] = mx;
            }
        }

        long ans = f[m - 1][n - 1][1];
        int mod = (int) 1e9 + 7;
        return ans < 0 ? -1 : (int) (ans % mod);
    }
}
