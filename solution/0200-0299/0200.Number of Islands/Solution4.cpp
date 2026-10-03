class Solution {
public:
    int numIslands(vector<vector<char>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
        int ans = 0;
        int dirs[5] = {-1, 0, 1, 0, -1};
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] != '1') {
                    continue;
                }
                vector<pair<int, int>> stk{{i, j}};
                grid[i][j] = '0';
                while (!stk.empty()) {
                    auto [a, b] = stk.back();
                    stk.pop_back();
                    for (int k = 0; k < 4; ++k) {
                        int x = a + dirs[k], y = b + dirs[k + 1];
                        if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1') {
                            grid[x][y] = '0';
                            stk.emplace_back(x, y);
                        }
                    }
                }
                ++ans;
            }
        }
        return ans;
    }
};
