class Solution {
    public int numRollsToTarget(int d, int f, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= d; ++i) {
            for (int j = target; j >= 1; --j) {
                for (int k = 1; k <= f && k <= j; ++k) {
                    dp[j] = (dp[j] + dp[j - k]) % 1000000007;
                }
            }
        }
        return dp[target];
    }
}
