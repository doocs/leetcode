class Solution {
    private long[][] memo;
    private int[][] d;

    public long sellingWood(int m, int n, int[][] prices) {
        d = new int[m + 1][n + 1];
        memo = new long[m + 1][n + 1];
        for (long[] e : memo) {
            Arrays.fill(e, -1);
        }
        for (int[] p : prices) {
            d[p[0]][p[1]] = p[2];
        }
        return dfs(m, n);
    }

    private long dfs(int m, int n) {
        if (memo[m][n] != -1) {
            return memo[m][n];
        }

        long ans = d[m][n];
        for (int i = 1; i < m / 2 + 1; ++i) {
            ans = Math.max(ans, dfs(i, n) + dfs(m - i, n));
        }
        for (int i = 1; i < n / 2 + 1; ++i) {
            ans = Math.max(ans, dfs(m, i) + dfs(m, n - i));
        }
        memo[m][n] = ans;
        return ans;
    }
}