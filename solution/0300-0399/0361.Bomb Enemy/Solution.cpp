class Solution {
public:
    int maxKilledEnemies(vector<vector<char>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<int>> g(m, vector<int>(n));
        for (int i = 0; i < m; ++i) {
            int t = 0;
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 'W')
                    t = 0;
                else if (grid[i][j] == 'E')
                    ++t;
                g[i][j] += t;
            }
            t = 0;
            for (int j = n - 1; j >= 0; --j) {
                if (grid[i][j] == 'W')
                    t = 0;
                else if (grid[i][j] == 'E')
                    ++t;
                g[i][j] += t;
            }
        }
        for (int j = 0; j < n; ++j) {
            int t = 0;
            for (int i = 0; i < m; ++i) {
                if (grid[i][j] == 'W')
                    t = 0;
                else if (grid[i][j] == 'E')
                    ++t;
                g[i][j] += t;
            }
            t = 0;
            for (int i = m - 1; i >= 0; --i) {
                if (grid[i][j] == 'W')
                    t = 0;
                else if (grid[i][j] == 'E')
                    ++t;
                g[i][j] += t;
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '0') ans = max(ans, g[i][j]);
            }
        }
        return ans;
    }
};