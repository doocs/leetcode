class Solution {
    public int maxSumDivThree(int[] nums) {
        int[] dp = new int[3];
        for (int v : nums) {
            int a = dp[0] + v, b = dp[1] + v, c = dp[2] + v;
            dp[a % 3] = Math.max(dp[a % 3], a);
            dp[b % 3] = Math.max(dp[b % 3], b);
            dp[c % 3] = Math.max(dp[c % 3], c);
        }
        return dp[0];
    }
}
