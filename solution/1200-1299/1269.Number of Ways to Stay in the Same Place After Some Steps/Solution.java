class Solution {
    private Integer[][] f;
    private int n;

    public int numWays(int steps, int arrLen) {
        f = new Integer[steps][steps + 1];
        n = arrLen;
        return dfs(0, steps);
    }

    private int dfs(int i, int j) {
        if (i > j || i >= n || i < 0 || j < 0) {
            return 0;
        }
        if (i == 0 && j == 0) {
            return 1;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        int ans = 0;
        final int mod = (int) 1e9 + 7;
        for (int k = -1; k <= 1; ++k) {
            ans = (ans + dfs(i + k, j - 1)) % mod;
        }
        return f[i][j] = ans;
    }
}