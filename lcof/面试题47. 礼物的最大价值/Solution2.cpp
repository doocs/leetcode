class Solution {
public:
    int maxValue(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<int>> f(2, vector<int>(n + 1, 0));
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                f[i & 1][j] = max(f[i & 1 ^ 1][j], f[i & 1][j - 1]) + grid[i - 1][j - 1];
            }
        }
        return f[m & 1][n];
    }
};