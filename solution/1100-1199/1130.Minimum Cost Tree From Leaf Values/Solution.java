class Solution {
    private Integer[][] f;
    private int[][] g;

    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;
        f = new Integer[n][n];
        g = new int[n][n];
        for (int i = 0; i < n; i++) {
            g[i][i] = arr[i];
            for (int j = i + 1; j < n; j++) {
                g[i][j] = Math.max(g[i][j - 1], arr[j]);
            }
        }
        return dfs(0, n - 1);
    }

    private int dfs(int i, int j) {
        if (i == j) {
            return 0;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        int ans = 1 << 30;
        for (int k = i; k < j; k++) {
            ans = Math.min(ans, dfs(i, k) + dfs(k + 1, j) + g[i][k] * g[k + 1][j]);
        }
        return f[i][j] = ans;
    }
}