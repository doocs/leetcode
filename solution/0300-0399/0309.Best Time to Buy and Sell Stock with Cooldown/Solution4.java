class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] f = new int[n + 2][2];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = 0; j < 2; ++j) {
                int ans = f[i + 1][j];
                if (j > 0) {
                    ans = Math.max(ans, prices[i] + f[i + 2][0]);
                } else {
                    ans = Math.max(ans, -prices[i] + f[i + 1][1]);
                }
                f[i][j] = ans;
            }
        }
        return f[0][0];
    }
}
