class Solution {
public:
    int uniquePathsIII(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int cnt = 0;
        for (auto& row : grid) {
            for (auto& x : row) {
                cnt += x == 0;
            }
        }
        int dirs[5] = {-1, 0, 1, 0, -1};
        bool vis[m][n];
        memset(vis, false, sizeof vis);
        function<int(int, int, int)> dfs = [&](int i, int j, int k) -> int {
            if (grid[i][j] == 2) {
                return k == cnt + 1 ? 1 : 0;
            }
            int ans = 0;
            for (int h = 0; h < 4; ++h) {
                int x = i + dirs[h], y = j + dirs[h + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && !vis[x][y] && grid[x][y] != -1) {
                    vis[x][y] = true;
                    ans += dfs(x, y, k + 1);
                    vis[x][y] = false;
                }
            }
            return ans;
        };
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    vis[i][j] = true;
                    return dfs(i, j, 0);
                }
            }
        }
        return 0;
    }
};