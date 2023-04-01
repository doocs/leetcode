class Solution {
    private int n;
    private int[] values;
    private Integer[][] f;

    public int minScoreTriangulation(int[] values) {
        n = values.length;
        this.values = values;
        f = new Integer[n][n];
        return dfs(0, n - 1);
    }

    private int dfs(int i, int j) {
        if (i + 1 == j) {
            return 0;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        int ans = 1 << 30;
        for (int k = i + 1; k < j; ++k) {
            ans = Math.min(ans, dfs(i, k) + dfs(k, j) + values[i] * values[k] * values[j]);
        }
        return f[i][j] = ans;
    }
}