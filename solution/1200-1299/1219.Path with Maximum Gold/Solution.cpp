class Solution {
public:
    vector<vector<int>> grid;
    vector<vector<int>> dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    int getMaximumGold(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        this->grid = grid;
        vector<vector<bool>> vis(m, vector<bool>(n, false));
        int ans = 0;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                ans = max(ans, dfs(i, j, vis));
        return ans;
    }

    int dfs(int i, int j,  vector<vector<bool>>& vis) {
        if (i < 0 || i >= grid.size() || j < 0 || j >= grid[0].size() || grid[i][j] == 0 || vis[i][j]) return 0;
        vis[i][j] = true;
        int t = 0;
        for (auto& dir : dirs)
            t = max(t, dfs(i + dir[0], j + dir[1], vis));
        vis[i][j] = false;
        return t + grid[i][j];
    }
};