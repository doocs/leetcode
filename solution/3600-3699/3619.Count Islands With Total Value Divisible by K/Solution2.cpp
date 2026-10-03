class Solution {
public:
    int countIslands(vector<vector<int>>& grid, int k) {
        int m = grid.size(), n = grid[0].size();
        int dirs[5] = {-1, 0, 1, 0, -1};
        vector<pair<int, int>> stk;
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (!grid[i][j]) {
                    continue;
                }
                long long s = grid[i][j];
                grid[i][j] = 0;
                stk.emplace_back(i, j);
                while (!stk.empty()) {
                    auto [x0, y0] = stk.back();
                    stk.pop_back();
                    for (int d = 0; d < 4; ++d) {
                        int x = x0 + dirs[d], y = y0 + dirs[d + 1];
                        if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y]) {
                            s += grid[x][y];
                            grid[x][y] = 0;
                            stk.emplace_back(x, y);
                        }
                    }
                }
                if (s % k == 0) {
                    ++ans;
                }
            }
        }
        return ans;
    }
};
