class Solution {
    private int m;
    private int n;
    private int[][][] f;
    private static final int[] DIRS = {-1, 0, 1, 0, -1};
    private static final int MOD = (int) 1e9 + 7;

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        this.m = m;
        this.n = n;
        f = new int[m + 1][n + 1][maxMove + 1];
        for (var a : f) {
            for (var b : a) {
                Arrays.fill(b, -1);
            }
        }
        return dfs(startRow, startColumn, maxMove);
    }

    private int dfs(int i, int j, int k) {
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return 1;
        }
        if (f[i][j][k] != -1) {
            return f[i][j][k];
        }
        if (k == 0) {
            return 0;
        }
        int res = 0;
        for (int t = 0; t < 4; ++t) {
            int x = i + DIRS[t];
            int y = j + DIRS[t + 1];
            res += dfs(x, y, k - 1);
            res %= MOD;
        }
        f[i][j][k] = res;
        return res;
    }
}