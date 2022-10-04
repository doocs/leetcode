class Solution {
public:
    int longestValidParentheses(string s) {
        int n = s.size();
        if (n < 2) return 0;
        vector<int> dp(n);
        int ans = 0;
        for (int i = 1; i < n; ++i) {
            if (s[i] == ')') {
                if (s[i - 1] == '(') {
                    dp[i] = 2 + (i > 1 ? dp[i - 2] : 0);
                } else {
                    int j = i - dp[i - 1] - 1;
                    if (~j && s[j] == '(') {
                        dp[i] = 2 + dp[i - 1] + (j ? dp[j - 1] : 0);
                    }
                }
                ans = max(ans, dp[i]);
            }
        }
        return ans;
    }
};