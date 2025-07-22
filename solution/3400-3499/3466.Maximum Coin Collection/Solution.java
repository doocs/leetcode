class Solution {
    private int n;
    private int[] lane1;
    private int[] lane2;
    private Long[][][] f;

    public long maxCoins(int[] lane1, int[] lane2) {
        n = lane1.length;
        this.lane1 = lane1;
        this.lane2 = lane2;
        f = new Long[n][2][3];
        long ans = Long.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, dfs(i, 0, 2));
        }
        return ans;
    }

    private long dfs(int i, int j, int k) {
        if (i >= n) {
            return 0;
        }
        if (f[i][j][k] != null) {
            return f[i][j][k];
        }
        int x = j == 0 ? lane1[i] : lane2[i];
        long ans = Math.max(x, dfs(i + 1, j, k) + x);
        if (k > 0) {
            ans = Math.max(ans, dfs(i + 1, j ^ 1, k - 1) + x);
            ans = Math.max(ans, dfs(i, j ^ 1, k - 1));
        }
        return f[i][j][k] = ans;
    }
}
