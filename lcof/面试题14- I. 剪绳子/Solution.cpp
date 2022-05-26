class Solution {
public:
    int cuttingRope(int n) {
        vector<int> dp(n + 1);
        dp[0] = 1;
        for (int i = 1; i < n; ++i) {
            for (int j = i; j <= n; ++j) {
                dp[j] = max(dp[j], dp[j - i] * i);
            }
        }
        return dp[n];
    }
};
