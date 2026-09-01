class Solution {
    public long maximumScore(int[][] grid) {
        int n = grid.length;
        final long inf = Long.MIN_VALUE / 2;
        long[][] s = new long[n][n + 1];
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < n; ++i) {
                s[j][i + 1] = s[j][i] + grid[i][j];
            }
        }
        long[][] f = new long[n + 1][n + 1];
        for (long[] row : f) {
            Arrays.fill(row, inf);
        }
        for (int h = 0; h <= n; ++h) {
            f[h][0] = 0;
        }
        for (int j = 0; j < n - 1; ++j) {
            long[][] g = new long[n + 1][n + 1];
            for (long[] row : g) {
                Arrays.fill(row, inf);
            }
            for (int h1 = 0; h1 <= n; ++h1) {
                long[] pre = new long[n + 2];
                pre[0] = f[h1][0];
                for (int h2 = 1; h2 <= n; ++h2) {
                    pre[h2] = Math.max(pre[h2 - 1], f[h1][h2]);
                }
                long[] suf = new long[n + 2];
                Arrays.fill(suf, inf);
                for (int h2 = n; h2 >= 0; --h2) {
                    long v = f[h1][h2] == inf ? inf : f[h1][h2] + Math.max(0, s[j][h2] - s[j][h1]);
                    suf[h2] = Math.max(suf[h2 + 1], v);
                }
                for (int hp = 0; hp <= n; ++hp) {
                    long add = Math.max(0, s[j][hp] - s[j][h1]);
                    long v1 = pre[hp] == inf ? inf : pre[hp] + add;
                    g[hp][h1] = Math.max(v1, suf[hp + 1]);
                }
            }
            f = g;
        }
        long ans = 0;
        for (int h1 = 0; h1 <= n; ++h1) {
            for (int h2 = 0; h2 <= n; ++h2) {
                if (f[h1][h2] != inf) {
                    ans = Math.max(ans, f[h1][h2] + Math.max(0, s[n - 1][h2] - s[n - 1][h1]));
                }
            }
        }
        return ans;
    }
}
