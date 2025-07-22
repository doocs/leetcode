class Solution {
    private Integer[][][] f;
    private int[][] coins;
    private int m;
    private int n;

    public int maximumAmount(int[][] coins) {
        m = coins.length;
        n = coins[0].length;
        this.coins = coins;
        f = new Integer[m][n][3];
        return dfs(0, 0, 2);
    }

    private int dfs(int i, int j, int k) {
        if (i >= m || j >= n) {
            return Integer.MIN_VALUE / 2;
        }
        if (f[i][j][k] != null) {
            return f[i][j][k];
        }
        if (i == m - 1 && j == n - 1) {
            return k > 0 ? Math.max(0, coins[i][j]) : coins[i][j];
        }
        int ans = coins[i][j] + Math.max(dfs(i + 1, j, k), dfs(i, j + 1, k));
        if (coins[i][j] < 0 && k > 0) {
            ans = Math.max(ans, Math.max(dfs(i + 1, j, k - 1), dfs(i, j + 1, k - 1)));
        }
        return f[i][j][k] = ans;
    }
}
