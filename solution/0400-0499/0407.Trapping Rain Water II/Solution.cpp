class Solution {
public:
    int trapRainWater(vector<vector<int>>& heightMap) {
        using tii = tuple<int, int, int>;
        priority_queue<tii, vector<tii>, greater<tii>> pq;
        int m = heightMap.size(), n = heightMap[0].size();
        bool vis[m][n];
        memset(vis, 0, sizeof vis);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    pq.emplace(heightMap[i][j], i, j);
                    vis[i][j] = true;
                }
            }
        }
        int ans = 0;
        int dirs[5] = {-1, 0, 1, 0, -1};
        while (!pq.empty()) {
            auto [h, i, j] = pq.top();
            pq.pop();
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && !vis[x][y]) {
                    ans += max(0, h - heightMap[x][y]);
                    vis[x][y] = true;
                    pq.emplace(max(heightMap[x][y], h), x, y);
                }
            }
        }
        return ans;
    }
};