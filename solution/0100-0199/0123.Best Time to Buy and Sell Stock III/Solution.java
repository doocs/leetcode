class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) return 0;
        int m = 2 , n = prices.length;
        int[][] dp = new int[m+1][n];
        for (int i = 1; i <= m; i++) {
            int maxdiff = Integer.MIN_VALUE;
            for (int j = 1; j < n; j++) {
                maxdiff = Math.max(maxdiff, dp[i-1][j-1] - prices[j-1]);
                dp[i][j] = Math.max(dp[i][j-1], prices[j] + maxdiff);
            }
        }
        return dp[m][n-1];
    }
}