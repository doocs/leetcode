class Solution {
public:
    int numOfArrays(int n, int m, int k) {
        if (k == 0) return 0;
        int mod = 1e9 + 7;
        using ll = long long;
        vector<vector<vector<ll>>> dp(n + 1, vector<vector<ll>>(k + 1, vector<ll>(m + 1)));
        for (int i = 1; i <= m; ++i) dp[1][1][i] = 1;
        for (int i = 2; i <= n; ++i) {
            for (int c = 1; c <= min(i, k); ++c) {
                for (int j = 1; j <= m; ++j) {
                    dp[i][c][j] = (dp[i - 1][c][j] * j) % mod;
                    for (int j0 = 1; j0 < j; ++j0) {
                        dp[i][c][j] = (dp[i][c][j] + dp[i - 1][c - 1][j0]) % mod;
                    }
                }
            }
        }
        ll ans = 0;
        for (int i = 1; i <= m; ++i) ans = (ans + dp[n][k][i]) % mod;
        return (int) ans;
    }
};