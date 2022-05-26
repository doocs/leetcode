class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        boolean[] dp = new boolean[n];
        dp[0] = true;
        int[] preSum = new int[n + 1];
        preSum[1] = 1;
        for (int i = 1; i < n; ++i) {
            if (s.charAt(i) == '0') {
                int l = Math.max(0, i - maxJump);
                int r = i - minJump;
                if (r >= l && preSum[r + 1] - preSum[l] > 0) {
                    dp[i] = true;
                }
            }
            preSum[i + 1] = preSum[i] + (dp[i] ? 1 : 0);
        }
        return dp[n - 1];
    }
}