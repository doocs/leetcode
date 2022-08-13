class Solution {
public:
    int m, n;
    vector<vector<int>> dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    vector<vector<int>> colorBorder(vector<vector<int>>& grid, int row, int col, int color) {
        m = grid.size();
        n = grid[0].size();
        vector<vector<bool>> vis(m, vector<bool>(n, false));
        dfs(row, col, color, grid, vis);
        return grid;
    }

    void dfs(int i, int j, int color, vector<vector<int>>& grid, vector<vector<bool>>& vis) {
        vis[i][j] = true;
        int oldColor = grid[i][j];
        for (auto& dir : dirs) {
            int x = i + dir[0], y = j + dir[1];
            if (x >= 0 && x < m && y >= 0 && y < n) {
                if (!vis[x][y]) {
                    if (grid[x][y] == oldColor)
                        dfs(x, y, color, grid, vis);
                    else
                        grid[i][j] = color;
                }
            } else
                grid[i][j] = color;
        }
    }
};