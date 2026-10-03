class Solution {
public:
    int numEnclaves(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        const int dirs[5] = {-1, 0, 1, 0, -1};
        auto flood = [&](int i, int j) {
            vector<pair<int, int>> stk{{i, j}};
            grid[i][j] = 0;
            while (!stk.empty()) {
                auto [a, b] = stk.back();
                stk.pop_back();
                for (int k = 0; k < 4; ++k) {
                    int x = a + dirs[k], y = b + dirs[k + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                        grid[x][y] = 0;
                        stk.emplace_back(x, y);
                    }
                }
            }
        };
        for (int j = 0; j < n; ++j) {
            for (int i : {0, m - 1}) {
                if (grid[i][j] == 1) {
                    flood(i, j);
                }
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j : {0, n - 1}) {
                if (grid[i][j] == 1) {
                    flood(i, j);
                }
            }
        }
        int ans = 0;
        for (const auto& row : grid) {
            ans += accumulate(row.begin(), row.end(), 0);
        }
        return ans;
    }
};
