class Solution {
public:
    int getMoneyAmount(int n) {
        vector<vector<int>> dp(n + 10, vector<int>(n + 10));
        for (int l = 2; l <= n; ++l) {
            for (int i = 1; i + l - 1 <= n; ++i) {
                int j = i + l - 1;
                dp[i][j] = INT_MAX;
                for (int k = i; k <= j; ++k) {
                    int t = max(dp[i][k - 1], dp[k + 1][j]) + k;
                    dp[i][j] = min(dp[i][j], t);
                }
            }
        }
        return dp[1][n];
    }
};