class Solution {
public:
    int longestRepeatingSubstring(string s) {
        int n = s.size();
        vector<vector<int>> dp(n, vector<int>(n));
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (s[i] == s[j]) {
                    dp[i][j] = i ? dp[i - 1][j - 1] + 1 : 1;
                    ans = max(ans, dp[i][j]);
                }
            }
        }
        return ans;
    }
};