class Solution {
public:
    int countVowelPermutation(int n) {
        using ll = long long;
        const ll mod = 1e9 + 7;
        vector<ll> dp(5, 1);
        vector<ll> t(5);
        for (int i = 0; i < n - 1; ++i) {
            t[0] = (dp[1] + dp[2] + dp[4]) % mod;
            t[1] = (dp[0] + dp[2]) % mod;
            t[2] = (dp[1] + dp[3]) % mod;
            t[3] = dp[2];
            t[4] = (dp[2] + dp[3]) % mod;
            dp = t;
        }
        return accumulate(dp.begin(), dp.end(), 0LL) % mod;
    }
};