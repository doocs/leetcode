class Solution {
public:
    const int mod = 1e9 + 7;
    int countPaths(vector<vector<int>>& grid) {
        int ans = 0;
        vector<vector<int>> f(grid.size(), vector<int>(grid[0].size()));
        for (int i = 0; i < grid.size(); ++i)
            for (int j = 0; j < grid[0].size(); ++j)
                ans = (ans + dfs(i, j, f, grid)) % mod;
        return ans;
    }

    int dfs(int i, int j, vector<vector<int>>& f, vector<vector<int>>& g) {
        if (f[i][j]) return f[i][j];
        int res = 1;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < g.size() && y >= 0 && y < g[0].size() && g[x][y] > g[i][j])
                res = (res + dfs(x, y, f, g)) % mod;
        }
        f[i][j] = res;
        return res;
    }
};