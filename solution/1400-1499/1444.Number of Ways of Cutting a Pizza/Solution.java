class Solution {
    private static final int MOD = (int) 1e9 + 7;
    private int[][][] f;
    private int[][] s;
    private int m;
    private int n;

    public int ways(String[] pizza, int k) {
        m = pizza.length;
        n = pizza[0].length();
        s = new int[m + 1][n + 1];
        f = new int[m][n][k];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                s[i + 1][j + 1] = s[i + 1][j] + s[i][j + 1] - s[i][j] + (pizza[i].charAt(j) == 'A' ? 1 : 0);
                Arrays.fill(f[i][j], -1);
            }
        }
        return dfs(0, 0, k - 1);
    }

    private int dfs(int i, int j, int k) {
        if (f[i][j][k] != -1) {
            return f[i][j][k];
        }
        if (k == 0) {
            return s[m][n] - s[m][j] - s[i][n] + s[i][j] > 0 ? 1 : 0;
        }
        int res = 0;
        for (int x = i + 1; x < m; ++x) {
            if (s[x][n] - s[x][j] - s[i][n] + s[i][j] > 0) {
                res = (res + dfs(x, j, k - 1)) % MOD;
            }
        }
        for (int y = j + 1; y < n; ++y) {
            if (s[m][y] - s[m][j] - s[i][y] + s[i][j] > 0) {
                res = (res + dfs(i, y, k - 1)) % MOD;
            }
        }
        f[i][j][k] = res;
        return res;
    }
}