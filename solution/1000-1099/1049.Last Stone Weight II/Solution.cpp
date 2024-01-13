class Solution {
public:
    int lastStoneWeightII(vector<int>& stones) {
        int s = accumulate(stones.begin(), stones.end(), 0);
        int m = stones.size(), n = s >> 1;
        vector<vector<int>> dp(m + 1, vector<int>(n + 1));
        for (int i = 1; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                dp[i][j] = dp[i - 1][j];
                if (stones[i - 1] <= j) dp[i][j] = max(dp[i][j], dp[i - 1][j - stones[i - 1]] + stones[i - 1]);
            }
        }
        return s - dp[m][n] * 2;
    }
};