class Solution {
    private int[][] f;
    private int[][] c;
    private int n;
    private int m;
    private static final int MOD = (int) 1e9 + 7;

    public int idealArrays(int n, int maxValue) {
        this.n = n;
        this.m = maxValue;
        this.f = new int[maxValue + 1][16];
        for (int[] row : f) {
            Arrays.fill(row, -1);
        }
        c = new int[n][16];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= i && j < 16; ++j) {
                c[i][j] = j == 0 ? 1 : (c[i - 1][j] + c[i - 1][j - 1]) % MOD;
            }
        }
        int ans = 0;
        for (int i = 1; i <= m; ++i) {
            ans = (ans + dfs(i, 1)) % MOD;
        }
        return ans;
    }

    private int dfs(int i, int cnt) {
        if (f[i][cnt] != -1) {
            return f[i][cnt];
        }
        int res = c[n - 1][cnt - 1];
        if (cnt < n) {
            for (int k = 2; k * i <= m; ++k) {
                res = (res + dfs(k * i, cnt + 1)) % MOD;
            }
        }
        f[i][cnt] = res;
        return res;

    }
}