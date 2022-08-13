class Solution {
public:
    int m;
    int n;

    int numEnclaves(vector<vector<int>>& grid) {
        m = grid.size();
        n = grid[0].size();
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if (grid[i][j] == 1 && (i == 0 || i == m - 1 || j == 0 || j == n - 1))
                    dfs(i, j, grid);
        int ans = 0;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if (grid[i][j] == 1)
                    ++ans;
        return ans;
    }

    void dfs(int i, int j, vector<vector<int>>& grid) {
        grid[i][j] = 0;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k];
            int y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1)
                dfs(x, y, grid);
        }
    }
};