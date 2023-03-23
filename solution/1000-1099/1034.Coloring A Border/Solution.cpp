class Solution {
public:
    vector<vector<int>> colorBorder(vector<vector<int>>& grid, int row, int col, int color) {
        int m = grid.size();
        int n = grid[0].size();
        bool vis[m][n];
        memset(vis, false, sizeof(vis));
        int dirs[5] = {-1, 0, 1, 0, -1};
        function<void(int, int, int)> dfs = [&](int i, int j, int c) {
            vis[i][j] = true;
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k];
                int y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n) {
                    if (!vis[x][y]) {
                        if (grid[x][y] == c) {
                            dfs(x, y, c);
                        } else {
                            grid[i][j] = color;
                        }
                    }
                } else {
                    grid[i][j] = color;
                }
            }
        };
        dfs(row, col, grid[row][col]);
        return grid;
    }
};