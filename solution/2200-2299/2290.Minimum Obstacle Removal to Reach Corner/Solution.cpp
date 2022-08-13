class Solution {
public:
    int minimumObstacles(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        deque<tuple<int, int, int>> q {{0, 0, 0}};
        vector<vector<bool>> vis(m, vector<bool>(n));
        vector<int> dirs = {-1, 0, 1, 0, -1};
        while (!q.empty()) {
            auto [i, j, k] = q.front();
            q.pop_front();
            if (i == m - 1 && j == n - 1) return k;
            if (vis[i][j]) continue;
            vis[i][j] = true;
            for (int o = 0; o < 4; ++o) {
                int x = i + dirs[o], y = j + dirs[o + 1];
                if (x >= 0 && x < m && y >= 0 && y < n) {
                    if (grid[x][y] == 0)
                        q.push_front({x, y, k});
                    else
                        q.push_back({x, y, k + 1});
                }
            }
        }
        return 0;
    }
};