class Solution {
    public int longestValidParentheses(String s) {
        int n = s.length();
        if (n < 2) {
            return 0;
        }
        char[] cs = s.toCharArray();
        int[] dp = new int[n];
        int ans = 0;
        for (int i = 1; i < n; ++i) {
            if (cs[i] == ')') {
                if (cs[i - 1] == '(') {
                    dp[i] = 2 + (i > 1 ? dp[i - 2] : 0);
                } else {
                    int j = i - dp[i - 1] - 1;
                    if (j >= 0 && cs[j] == '(') {
                        dp[i] = 2 + dp[i - 1] + (j > 0 ? dp[j - 1] : 0);
                    }
                }
                ans = Math.max(ans, dp[i]);
            }
        }
        return ans;
    }
}