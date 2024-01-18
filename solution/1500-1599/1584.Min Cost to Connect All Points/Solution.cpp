class Solution {
public:
    int minCostConnectPoints(vector<vector<int>>& points) {
        int n = points.size();
        int g[n][n];
        for (int i = 0; i < n; ++i) {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = i + 1; j < n; ++j) {
                int x2 = points[j][0], y2 = points[j][1];
                int t = abs(x1 - x2) + abs(y1 - y2);
                g[i][j] = t;
                g[j][i] = t;
            }
        }
        int dist[n];
        bool vis[n];
        memset(dist, 0x3f, sizeof(dist));
        memset(vis, false, sizeof(vis));
        dist[0] = 0;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int j = -1;
            for (int k = 0; k < n; ++k) {
                if (!vis[k] && (j == -1 || dist[k] < dist[j])) {
                    j = k;
                }
            }
            vis[j] = true;
            ans += dist[j];
            for (int k = 0; k < n; ++k) {
                if (!vis[k]) {
                    dist[k] = min(dist[k], g[j][k]);
                }
            }
        }
        return ans;
    }
};