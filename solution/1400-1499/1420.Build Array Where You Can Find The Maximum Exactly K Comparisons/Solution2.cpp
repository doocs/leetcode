class Solution {
public:
    int numOfArrays(int n, int m, int k) {
        if (k == 0) return 0;
        constexpr int mod = 1e9 + 7;
        vector<vector<long long>> dp(k + 1, vector<long long>(m + 1));
        for (int j = 1; j <= m; ++j) dp[1][j] = 1;
        for (int i = 2; i <= n; ++i) {
            vector<vector<long long>> nxt(k + 1, vector<long long>(m + 1));
            for (int c = 1; c <= min(i, k); ++c) {
                long long prefix = 0;
                for (int j = 1; j <= m; ++j) {
                    prefix = (prefix + dp[c - 1][j - 1]) % mod;
                    nxt[c][j] = (dp[c][j] * j + prefix) % mod;
                }
            }
            dp.swap(nxt);
        }
        long long ans = 0;
        for (int j = 1; j <= m; ++j) ans = (ans + dp[k][j]) % mod;
        return ans;
    }
};
