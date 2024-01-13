class Solution {
public:
    int numEnclaves(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int dirs[5] = {-1, 0, 1, 0, -1};
        queue<pair<int, int>> q;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] && (i == 0 || i == m - 1 || j == 0 || j == n - 1)) {
                    q.emplace(i, j);
                    grid[i][j] = 0;
                }
            }
        }
        while (!q.empty()) {
            auto [i, j] = q.front();
            q.pop();
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y]) {
                    q.emplace(x, y);
                    grid[x][y] = 0;
                }
            }
        }
        int ans = 0;
        for (auto& row : grid) {
            for (auto& v : row) {
                ans += v;
            }
        }
        return ans;
    }
};