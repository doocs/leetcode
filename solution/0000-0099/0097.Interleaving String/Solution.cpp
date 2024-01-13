class Solution {
public:
    bool isInterleave(string s1, string s2, string s3) {
        int m = s1.size(), n = s2.size();
        if (m + n != s3.size()) {
            return false;
        }
        vector<vector<int>> f(m + 1, vector<int>(n + 1, -1));
        function<bool(int, int)> dfs = [&](int i, int j) {
            if (i >= m && j >= n) {
                return true;
            }
            if (f[i][j] != -1) {
                return f[i][j] == 1;
            }
            f[i][j] = 0;
            int k = i + j;
            if (i < m && s1[i] == s3[k] && dfs(i + 1, j)) {
                f[i][j] = 1;
            }
            if (!f[i][j] && j < n && s2[j] == s3[k] && dfs(i, j + 1)) {
                f[i][j] = 1;
            }
            return f[i][j] == 1;
        };
        return dfs(0, 0);
    }
};