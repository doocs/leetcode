class Solution {
    public int maxA(int n) {
        int[] dp = new int[n + 1];
        for (int i = 0; i < n + 1; ++i) {
            dp[i] = i;
        }
        for (int i = 3; i < n + 1; ++i) {
            for (int j = 2; j < i - 1; ++j) {
                dp[i] = Math.max(dp[i], dp[j - 1] * (i - j));
            }
        }
        return dp[n];
    }
}