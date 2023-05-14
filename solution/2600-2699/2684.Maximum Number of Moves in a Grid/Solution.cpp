class Solution {
public:
    int maxMoves(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int dist[m][n];
        memset(dist, 0, sizeof(dist));
        int ans = 0;
        queue<pair<int, int>> q;
        for (int i = 0; i < m; ++i) {
            q.emplace(i, 0);
        }
        int dirs[3][2] = {{-1, 1}, {0, 1}, {1, 1}};
        while (!q.empty()) {
            auto [i, j] = q.front();
            q.pop();
            for (int k = 0; k < 3; ++k) {
                int x = i + dirs[k][0], y = j + dirs[k][1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > grid[i][j] && dist[x][y] < dist[i][j] + 1) {
                    dist[x][y] = dist[i][j] + 1;
                    ans = max(ans, dist[x][y]);
                    q.emplace(x, y);
                }
            }
        }
        return ans;
    }
};