class Solution {

    public int coinChange(int[] coins, int amount) {
        int m = coins.length;
        int[][] dp = new int[m + 1][amount + 1];
        for (int i = 0; i <= m; ++i) {
            Arrays.fill(dp[i], amount + 1);
        }
        dp[0][0] = 0;
        for (int i = 1; i <= m; ++i) {
            int v = coins[i - 1];
            for (int j = 0; j <= amount; ++j) {
                for (int k = 0; k * v <= j; ++k) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - k * v] + k);
                }
            }
        }
        return dp[m][amount] > amount ? -1 : dp[m][amount];
    }
}