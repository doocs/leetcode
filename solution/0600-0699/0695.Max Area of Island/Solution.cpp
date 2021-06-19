class Solution {
public:
    int maxAreaOfIsland(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int t = dfs(grid, i, j, m, n);
                res = max(res, t);
            }
        }
        return res;
    }
private:
    vector<vector<int>> directions = {{0, 1}, {0, - 1}, {1, 0}, {-1, 0}};

    int dfs(vector<vector<int>>& grid, int i, int j, int m, int n) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0) {
            return 0;
        }
        grid[i][j] = 0;
        int res = 1;
        for (auto direction : directions) {
            res += dfs(grid, i + direction[0], j + direction[1], m, n);
        }
        return res;
    }

};