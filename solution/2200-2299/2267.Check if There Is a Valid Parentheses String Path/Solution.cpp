class Solution {
public:
    bool hasValidPath(vector<vector<char>>& grid) {
        int m = grid.size(), n = grid[0].size();
        if ((m + n - 1) % 2 || grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }
        bool vis[m][n][m + n];
        memset(vis, false, sizeof(vis));
        int dirs[3] = {1, 0, 1};
        auto dfs = [&](auto&& dfs, int i, int j, int k) -> bool {
            if (vis[i][j][k]) {
                return false;
            }
            vis[i][j][k] = true;
            k += grid[i][j] == '(' ? 1 : -1;
            if (k < 0 || k > m - i + n - j) {
                return false;
            }
            if (i == m - 1 && j == n - 1) {
                return k == 0;
            }
            for (int d = 0; d < 2; ++d) {
                int x = i + dirs[d], y = j + dirs[d + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && dfs(dfs, x, y, k)) {
                    return true;
                }
            }
            return false;
        };
        return dfs(dfs, 0, 0, 0);
    }
};
