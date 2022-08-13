class Solution {
public:
    int m;
    int n;

    int maxAreaOfIsland(vector<vector<int>>& grid) {
        m = grid.size();
        n = grid[0].size();
        int ans = 0;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if (grid[i][j] == 1)
                    ans = max(ans, dfs(i, j, grid));
        return ans;
    }

    int dfs(int i, int j, vector<vector<int>>& grid) {
        grid[i][j] = 0;
        int ans = 1;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k];
            int y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1)
                ans += dfs(x, y, grid);
        }
        return ans;
    }
};