class Solution {
public:
    bool checkPartitioning(string s) {
        int n = s.size();
        vector<vector<bool>> g(n, vector<bool>(n, true));
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                g[i][j] = s[i] == s[j] && (i + 1 == j || g[i + 1][j - 1]);
            }
        }
        for (int i = 0; i < n - 2; ++i) {
            for (int j = i + 1; j < n - 1; ++j) {
                if (g[0][i] && g[i + 1][j] && g[j + 1][n - 1]) {
                    return true;
                }
            }
        }
        return false;
    }
};