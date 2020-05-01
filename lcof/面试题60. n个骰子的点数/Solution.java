class Solution {
    public double[] twoSum(int n) {
        int[][] dp = new int[n + 1][6 * n + 1];
        for (int j = 1; j <= 6; ++j) {
            dp[1][j] = 1;
        }
        for (int i = 2; i <= n; ++i) {
            for (int j = i; j <= 6 * i; ++j) {
                for (int k = 1; k <= 6 && j > k; ++k) {
                    dp[i][j] += dp[i - 1][j - k];
                }
            }
        }
        double[] res = new double[5 * n + 1];
        double all = Math.pow(6, n);
        for (int i = 0; i <= 5 * n; ++i) {
            res[i] = dp[n][n + i] * 1.0 / all;
        }
        return res;
    }
}