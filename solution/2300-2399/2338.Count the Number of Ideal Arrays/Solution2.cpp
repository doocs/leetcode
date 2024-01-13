using ll = long long;

class Solution {
public:
    const int mod = 1e9 + 7;

    int idealArrays(int n, int maxValue) {
        vector<vector<int>> c(n, vector<int>(16));
        for (int i = 0; i < n; ++i)
            for (int j = 0; j <= i && j < 16; ++j)
                c[i][j] = j == 0 ? 1 : (c[i - 1][j] + c[i - 1][j - 1]) % mod;
        vector<vector<ll>> dp(maxValue + 1, vector<ll>(16));
        for (int i = 1; i <= maxValue; ++i) dp[i][1] = 1;
        for (int j = 1; j < 15; ++j) {
            for (int i = 1; i <= maxValue; ++i) {
                int k = 2;
                for (; k * i <= maxValue; ++k) dp[k * i][j + 1] = (dp[k * i][j + 1] + dp[i][j]) % mod;
            }
        }
        ll ans = 0;
        for (int i = 1; i <= maxValue; ++i)
            for (int j = 1; j < 16; ++j)
                ans = (ans + dp[i][j] * c[n - 1][j - 1]) % mod;
        return (int) ans;
    }
};