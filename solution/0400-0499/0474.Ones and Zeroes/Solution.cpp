class Solution {
public:
    int findMaxForm(vector<string>& strs, int m, int n) {
        vector<vector<int>> dp(m + 1, vector<int>(n + 1));
        for (auto s : strs) {
            vector<int> t = count(s);
            for (int i = m; i >= t[0]; --i)
                for (int j = n; j >= t[1]; --j)
                    dp[i][j] = max(dp[i][j], dp[i - t[0]][j - t[1]] + 1);
        }
        return dp[m][n];
    }

    vector<int> count(string s) {
        int n0 = 0;
        for (char c : s)
            if (c == '0') ++n0;
        return {n0, (int)s.size() - n0};
    }
};