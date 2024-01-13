class Solution {
    public long minimumTime(int[] power) {
        int n = power.length;
        long[] dp = new long[1 << n];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = 0;
        for (int mask = 1; mask < 1 << n; ++mask) {
            int cnt = Integer.bitCount(mask);
            for (int i = 0; i < n; ++i) {
                if (((mask >> i) & 1) == 1) {
                    dp[mask] = Math.min(dp[mask], dp[mask ^ (1 << i)] + (power[i] + cnt - 1) / cnt);
                }
            }
        }
        return dp[(1 << n) - 1];
    }
}