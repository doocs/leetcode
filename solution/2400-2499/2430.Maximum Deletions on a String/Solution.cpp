class Solution {
public:
    int deleteString(string s) {
        int n = s.size();
        int lcp[n + 1][n + 1];
        memset(lcp, 0, sizeof lcp);
        for (int i = n - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (s[i] == s[j]) {
                    lcp[i][j] = 1 + lcp[i + 1][j + 1];
                }
            }
        }
        int dp[n];
        for (int i = n - 1; i >= 0; --i) {
            dp[i] = 1;
            for (int j = 1; j <= (n - i) / 2; ++j) {
                if (lcp[i][i + j] >= j) {
                    dp[i] = max(dp[i], dp[i + j] + 1);
                }
            }
        }
        return dp[0];
    }
};