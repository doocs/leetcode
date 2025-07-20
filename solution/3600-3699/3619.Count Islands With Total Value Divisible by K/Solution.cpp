class Solution {
public:
    int countIslands(vector<vector<int>>& grid, int k) {
        int m = grid.size(), n = grid[0].size();
        vector<int> dirs = {-1, 0, 1, 0, -1};

        auto dfs = [&](this auto&& dfs, int i, int j) -> long long {
            long long s = grid[i][j];
            grid[i][j] = 0;
            for (int d = 0; d < 4; ++d) {
                int x = i + dirs[d], y = j + dirs[d + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y]) {
                    s += dfs(x, y);
                }
            }
            return s;
        };

        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] && dfs(i, j) % k == 0) {
                    ++ans;
                }
            }
        }
        return ans;
    }
};
