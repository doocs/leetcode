class Solution {
public:
    int minimumTime(vector<vector<int>>& grid) {
        if (grid[0][1] > 1 && grid[1][0] > 1) {
            return -1;
        }
        int m = grid.size(), n = grid[0].size();
        int dist[m][n];
        memset(dist, 0x3f, sizeof dist);
        dist[0][0] = 0;
        using tii = tuple<int, int, int>;
        priority_queue<tii, vector<tii>, greater<tii>> pq;
        pq.emplace(0, 0, 0);
        int dirs[5] = {-1, 0, 1, 0, -1};
        while (1) {
            auto [t, i, j] = pq.top();
            pq.pop();
            if (i == m - 1 && j == n - 1) {
                return t;
            }
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n) {
                    int nt = t + 1;
                    if (nt < grid[x][y]) {
                        nt = grid[x][y] + (grid[x][y] - nt) % 2;
                    }
                    if (nt < dist[x][y]) {
                        dist[x][y] = nt;
                        pq.emplace(nt, x, y);
                    }
                }
            }
        }
    }
};