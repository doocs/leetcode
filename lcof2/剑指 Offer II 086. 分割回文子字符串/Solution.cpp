class Solution {
public:
    vector<vector<bool>> dp;
    vector<vector<string>> ans;
    int n;

    vector<vector<string>> partition(string s) {
        n = s.size();
        dp.assign(n, vector<bool>(n, true));
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                dp[i][j] = s[i] == s[j] && dp[i + 1][j - 1];
            }
        }
        vector<string> t;
        dfs(s, 0, t);
        return ans;
    }

    void dfs(string& s, int i, vector<string> t) {
        if (i == n) {
            ans.push_back(t);
            return;
        }
        for (int j = i; j < n; ++j) {
            if (dp[i][j]) {
                t.push_back(s.substr(i, j - i + 1));
                dfs(s, j + 1, t);
                t.pop_back();
            }
        }
    }
};