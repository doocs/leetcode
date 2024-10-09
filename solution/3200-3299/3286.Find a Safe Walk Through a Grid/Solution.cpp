class Solution {
public:
    bool findSafeWalk(vector<vector<int>>& grid, int health) {
        int m = grid.size();
        int n = grid[0].size();
        vector<vector<int>> dist(m, vector<int>(n, INT_MAX));
        dist[0][0] = grid[0][0];
        queue<pair<int, int>> q;
        q.emplace(0, 0);
        int dirs[5] = {-1, 0, 1, 0, -1};
        while (!q.empty()) {
            auto [x, y] = q.front();
            q.pop();
            for (int i = 0; i < 4; ++i) {
                int nx = x + dirs[i];
                int ny = y + dirs[i + 1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && dist[nx][ny] > dist[x][y] + grid[nx][ny]) {
                    dist[nx][ny] = dist[x][y] + grid[nx][ny];
                    q.emplace(nx, ny);
                }
            }
        }
        return dist[m - 1][n - 1] < health;
    }
};
