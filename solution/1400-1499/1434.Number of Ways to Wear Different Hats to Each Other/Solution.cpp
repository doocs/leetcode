using ll = long long;

class Solution {
public:
    int numberWays(vector<vector<int>>& hats) {
        vector<vector<int>> d(41);
        int n = hats.size();
        int mx = 0;
        for (int i = 0; i < n; ++i) {
            for (int& h : hats[i]) {
                d[h].push_back(i);
                mx = max(mx, h);
            }
        }
        vector<vector<ll>> dp(mx + 1, vector<ll>(1 << n));
        dp[0][0] = 1;
        int mod = 1e9 + 7;
        for (int i = 1; i <= mx; ++i) {
            for (int mask = 0; mask < 1 << n; ++mask) {
                dp[i][mask] = dp[i - 1][mask];
                for (int& j : d[i]) {
                    if ((mask >> j) & 1) {
                        dp[i][mask] = (dp[i][mask] + dp[i - 1][mask ^ (1 << j)]) % mod;
                    }
                }
            }
        }
        return dp[mx][(1 << n) - 1];
    }
};