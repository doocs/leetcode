class Solution {
public:
    long long minimumTime(vector<int>& power) {
        int n = power.size();
        vector<long long> dp(1 << n, LONG_MAX);
        dp[0] = 0;
        for (int mask = 1; mask < 1 << n; ++mask) {
            int cnt = __builtin_popcount(mask);
            for (int i = 0; i < n; ++i) {
                if ((mask >> i) & 1) {
                    dp[mask] = min(dp[mask], dp[mask ^ (1 << i)] + (power[i] + cnt - 1) / cnt);
                }
            }
        }
        return dp[(1 << n) - 1];
    }
};