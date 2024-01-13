class Solution {
    private final int mod = (int) 1e9 + 7;
    private int n;
    private Integer[][][] f;

    public int checkRecord(int n) {
        this.n = n;
        f = new Integer[n][2][3];
        return dfs(0, 0, 0);
    }

    private int dfs(int i, int j, int k) {
        if (i >= n) {
            return 1;
        }
        if (f[i][j][k] != null) {
            return f[i][j][k];
        }
        int ans = dfs(i + 1, j, 0);
        if (j == 0) {
            ans = (ans + dfs(i + 1, j + 1, 0)) % mod;
        }
        if (k < 2) {
            ans = (ans + dfs(i + 1, j, k + 1)) % mod;
        }
        return f[i][j][k] = ans;
    }
}