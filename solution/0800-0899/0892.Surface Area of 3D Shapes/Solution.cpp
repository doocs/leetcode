class Solution {
public:
    int surfaceArea(vector<vector<int>>& grid) {
        int n = grid.size();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j]) {
                    ans += 2 + grid[i][j] * 4;
                    if (i) ans -= min(grid[i][j], grid[i - 1][j]) * 2;
                    if (j) ans -= min(grid[i][j], grid[i][j - 1]) * 2;
                }
            }
        }
        return ans;
    }
};