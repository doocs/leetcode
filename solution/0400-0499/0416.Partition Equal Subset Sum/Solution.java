class Solution {
    public boolean canPartition(int[] nums) {
        int s = 0;
        for (int v : nums) {
            s += v;
        }
        if (s % 2 != 0) {
            return false;
        }
        int n = s >> 1;
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int v : nums) {
            for (int j = n; j >= v; --j) {
                dp[j] = dp[j] || dp[j - v];
            }
        }
        return dp[n];
    }
}