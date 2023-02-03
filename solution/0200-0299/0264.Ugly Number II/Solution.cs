public class Solution {
    public int NthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int p2 = 0, p3 = 0, p5 = 0;
        for (int i = 1; i < n; ++i) {
            int next2 = dp[p2] * 2, next3 = dp[p3] * 3, next5 = dp[p5] * 5;
            dp[i] = Math.Min(next2, Math.Min(next3, next5));
            if (dp[i] == next2) {
                ++p2;
            }
            if (dp[i] == next3) {
                ++p3;
            }
            if (dp[i] == next5) {
                ++p5;
            }
        }
        return dp[n - 1];
    }
}