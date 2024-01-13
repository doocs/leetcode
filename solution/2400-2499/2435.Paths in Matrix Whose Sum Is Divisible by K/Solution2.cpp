class Solution {
public:
    const int mod = 1e9 + 7;

    int numberOfPaths(vector<vector<int>>& grid, int k) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<vector<int>>> dp(m, vector<vector<int>>(n, vector<int>(k)));
        dp[0][0][grid[0][0] % k] = 1;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int s = 0; s < k; ++s) {
                    int t = ((s - grid[i][j] % k) + k) % k;
                    if (i) dp[i][j][s] += dp[i - 1][j][t];
                    if (j) dp[i][j][s] += dp[i][j - 1][t];
                    dp[i][j][s] %= mod;
                }
            }
        }
        return dp[m - 1][n - 1][0];
    }
};