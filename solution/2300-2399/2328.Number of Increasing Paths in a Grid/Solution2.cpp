class Solution {
public:
    int countPaths(vector<vector<int>>& grid) {
        const int mod = 1e9 + 7;
        int m = grid.size(), n = grid[0].size();
        vector<vector<int>> f(m, vector<int>(n, 1));
        vector<array<int, 3>> cells;
        cells.reserve(m * n);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                cells.push_back({grid[i][j], i, j});
            }
        }
        sort(cells.begin(), cells.end(), [](const array<int, 3>& a, const array<int, 3>& b) {
            return a[0] > b[0];
        });
        int dirs[5] = {-1, 0, 1, 0, -1};
        for (auto& cell : cells) {
            int i = cell[1], j = cell[2];
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[i][j] < grid[x][y]) {
                    f[i][j] = (f[i][j] + f[x][y]) % mod;
                }
            }
        }
        long long ans = 0;
        for (auto& row : f) {
            for (int v : row) {
                ans += v;
            }
        }
        return ans % mod;
    }
};
