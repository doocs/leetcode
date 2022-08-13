class Solution {
public:
    int islandPerimeter(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    ans += 4;
                    if (i < m - 1 && grid[i + 1][j] == 1) ans -= 2;
                    if (j < n - 1 && grid[i][j + 1] == 1) ans -= 2;
                }
            }
        }
        return ans;
    }
};