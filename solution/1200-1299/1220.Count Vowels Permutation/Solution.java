class Solution {
    private static final long MOD = (long) 1e9 + 7;

    public int countVowelPermutation(int n) {
        long[] dp = new long[5];
        long[] t = new long[5];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n - 1; ++i) {
            t[0] = (dp[1] + dp[2] + dp[4]) % MOD;
            t[1] = (dp[0] + dp[2]) % MOD;
            t[2] = (dp[1] + dp[3]) % MOD;
            t[3] = dp[2];
            t[4] = (dp[2] + dp[3]) % MOD;
            System.arraycopy(t, 0, dp, 0, 5);
        }
        long ans = 0;
        for (int i = 0; i < 5; ++i) {
            ans = (ans + dp[i]) % MOD;
        }
        return (int) ans;
    }
}