class Solution {
public:
    int minimumEffortPath(vector<vector<int>>& heights) {
        int m = heights.size(), n = heights[0].size();
        vector<vector<int>> dist(m, vector<int>(n, 0x3f3f3f3f));
        dist[0][0] = 0;
        priority_queue<tuple<int, int, int>, vector<tuple<int, int, int>>, greater<tuple<int, int, int>>> q;
        q.push({0, 0, 0});
        vector<int> dirs = {-1, 0, 1, 0, -1};
        while (!q.empty()) {
            auto [t, i, j] = q.top();
            q.pop();
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n) {
                    int nd = max(t, abs(heights[x][y] - heights[i][j]));
                    if (nd < dist[x][y]) {
                        dist[x][y] = nd;
                        q.push({nd, x, y});
                    }
                }
            }
        }
        return dist[m - 1][n - 1];
    }
};