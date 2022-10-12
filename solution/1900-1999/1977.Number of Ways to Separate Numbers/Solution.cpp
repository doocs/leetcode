class Solution {
public:
    const int mod = 1e9 + 7;

    int numberOfCombinations(string num) {
        int n = num.size();
        vector<vector<int>> lcp(n + 1, vector<int>(n + 1));
        for (int i = n - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (num[i] == num[j]) {
                    lcp[i][j] = 1 + lcp[i + 1][j + 1];
                }
            }
        }
        auto cmp = [&](int i, int j, int k) {
            int x = lcp[i][j];
            return x >= k || num[i + x] >= num[j + x];
        };
        vector<vector<int>> dp(n + 1, vector<int>(n + 1));
        dp[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                int v = 0;
                if (num[i - j] != '0') {
                    if (i - j - j >= 0 && cmp(i - j, i - j - j, j)) {
                        v = dp[i - j][j];
                    } else {
                        v = dp[i - j][min(j - 1, i - j)];
                    }
                }
                dp[i][j] = (dp[i][j - 1] + v) % mod;
            }
        }
        return dp[n][n];
    }
};