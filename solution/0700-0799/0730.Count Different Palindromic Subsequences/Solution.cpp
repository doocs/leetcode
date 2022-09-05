using ll = long long;

class Solution {
public:
    int countPalindromicSubsequences(string s) {
        int mod = 1e9 + 7;
        int n = s.size();
        vector<vector<vector<ll>>> dp(n, vector<vector<ll>>(n, vector<ll>(4)));
        for (int i = 0; i < n; ++i) dp[i][i][s[i] - 'a'] = 1;
        for (int l = 2; l <= n; ++l) {
            for (int i = 0; i + l <= n; ++i) {
                int j = i + l - 1;
                for (char c = 'a'; c <= 'd'; ++c) {
                    int k = c - 'a';
                    if (s[i] == c && s[j] == c)
                        dp[i][j][k] = 2 + accumulate(dp[i + 1][j - 1].begin(), dp[i + 1][j - 1].end(), 0ll) % mod;
                    else if (s[i] == c)
                        dp[i][j][k] = dp[i][j - 1][k];
                    else if (s[j] == c)
                        dp[i][j][k] = dp[i + 1][j][k];
                    else
                        dp[i][j][k] = dp[i + 1][j - 1][k];
                }
            }
        }
        ll ans = accumulate(dp[0][n - 1].begin(), dp[0][n - 1].end(), 0ll);
        return (int) (ans % mod);
    }
};