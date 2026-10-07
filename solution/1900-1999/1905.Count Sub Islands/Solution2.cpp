class Solution {
public:
    int countSubIslands(vector<vector<int>>& grid1, vector<vector<int>>& grid2) {
        int m = grid1.size(), n = grid1[0].size();
        int ans = 0;
        int dirs[5] = {-1, 0, 1, 0, -1};
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (!grid2[i][j]) {
                    continue;
                }
                int ok = 1;
                vector<pair<int, int>> stk{{i, j}};
                grid2[i][j] = 0;
                while (!stk.empty()) {
                    auto [a, b] = stk.back();
                    stk.pop_back();
                    ok &= grid1[a][b];
                    for (int k = 0; k < 4; ++k) {
                        int x = a + dirs[k], y = b + dirs[k + 1];
                        if (x >= 0 && x < m && y >= 0 && y < n && grid2[x][y]) {
                            grid2[x][y] = 0;
                            stk.emplace_back(x, y);
                        }
                    }
                }
                ans += ok;
            }
        }
        return ans;
    }
};
