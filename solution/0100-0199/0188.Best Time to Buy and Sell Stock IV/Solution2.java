class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int[][][] f = new int[n][k + 1][2];
        for (int j = 1; j <= k; ++j) {
            f[0][j][1] = -prices[0];
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j <= k; ++j) {
                f[i][j][0] = Math.max(f[i - 1][j][1] + prices[i], f[i - 1][j][0]);
                f[i][j][1] = Math.max(f[i - 1][j - 1][0] - prices[i], f[i - 1][j][1]);
            }
        }
        return f[n - 1][k][0];
    }
}