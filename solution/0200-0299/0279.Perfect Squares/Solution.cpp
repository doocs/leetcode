class Solution {
public:
    int numSquares(int n) {
        vector<int> dp(n + 1);
        for (int i = 1; i <= n; ++i) {
            int mi = 100000;
            for (int j = 1; j * j <= i; ++j) {
                mi = min(mi, dp[i - j * j]);
            }
            dp[i] = mi + 1;
        }
        return dp[n];
    }
};