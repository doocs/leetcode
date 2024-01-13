class Solution {
public:
    int minSteps(int n) {
        vector<int> dp(n + 1);
        iota(dp.begin(), dp.end(), 0);
        dp[1] = 0;
        for (int i = 2; i < n + 1; ++i) {
            for (int j = 2; j * j <= i; ++j) {
                if (i % j == 0) {
                    dp[i] = min(dp[i], dp[i / j] + j);
                }
            }
        }
        return dp[n];
    }
};