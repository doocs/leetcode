class Solution {
public:
    int maxPalindromes(string s, int k) {
        int n = s.size();
        vector<vector<bool>> dp(n, vector<bool>(n, true));
        vector<int> f(n, -1);
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                dp[i][j] = s[i] == s[j] && dp[i + 1][j - 1];
            }
        }
        function<int(int)> dfs = [&](int i) -> int {
            if (i >= n) return 0;
            if (f[i] != -1) return f[i];
            int ans = dfs(i + 1);
            for (int j = i + k - 1; j < n; ++j) {
                if (dp[i][j]) {
                    ans = max(ans, 1 + dfs(j + 1));
                }
            }
            f[i] = ans;
            return ans;
        };
        return dfs(0);
    }
};