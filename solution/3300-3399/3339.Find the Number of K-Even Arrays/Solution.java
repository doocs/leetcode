class Solution {
    private Integer[][][] f;
    private long cnt0, cnt1;
    private final int mod = (int) 1e9 + 7;

    public int countOfArrays(int n, int m, int k) {
        f = new Integer[n][k + 1][2];
        cnt0 = m / 2;
        cnt1 = m - cnt0;
        return dfs(0, k, 1);
    }

    private int dfs(int i, int j, int k) {
        if (j < 0) {
            return 0;
        }
        if (i >= f.length) {
            return j == 0 ? 1 : 0;
        }
        if (f[i][j][k] != null) {
            return f[i][j][k];
        }
        int a = (int) (cnt1 * dfs(i + 1, j, 1) % mod);
        int b = (int) (cnt0 * dfs(i + 1, j - (k & 1 ^ 1), 0) % mod);
        return f[i][j][k] = (a + b) % mod;
    }
}
