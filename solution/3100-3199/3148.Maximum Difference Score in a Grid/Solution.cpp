class Solution {
public:
    int maxScore(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        const int inf = 1 << 30;
        int ans = -inf;
        int f[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int mi = inf;
                if (i) {
                    mi = min(mi, f[i - 1][j]);
                }
                if (j) {
                    mi = min(mi, f[i][j - 1]);
                }
                ans = max(ans, grid[i][j] - mi);
                f[i][j] = min(grid[i][j], mi);
            }
        }
        return ans;
    }
};