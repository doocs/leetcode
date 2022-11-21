class Solution {
public:
    int longestLine(vector<vector<int>>& mat) {
        int m = mat.size(), n = mat[0].size();
        vector<vector<int>> a(m + 2, vector<int>(n + 2));
        vector<vector<int>> b(m + 2, vector<int>(n + 2));
        vector<vector<int>> c(m + 2, vector<int>(n + 2));
        vector<vector<int>> d(m + 2, vector<int>(n + 2));
        int ans = 0;
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (mat[i - 1][j - 1]) {
                    a[i][j] = a[i - 1][j] + 1;
                    b[i][j] = b[i][j - 1] + 1;
                    c[i][j] = c[i - 1][j - 1] + 1;
                    d[i][j] = d[i - 1][j + 1] + 1;
                    ans = max(ans, max(a[i][j], max(b[i][j], max(c[i][j], d[i][j]))));
                }
            }
        }
        return ans;
    }
};