class Solution {
public:
    int findTheCity(int n, vector<vector<int>>& edges, int distanceThreshold) {
        int g[n][n];
        int dist[n];
        bool vis[n];
        memset(g, 0x3f, sizeof(g));
        for (auto& e : edges) {
            int f = e[0], t = e[1], w = e[2];
            g[f][t] = g[t][f] = w;
        }
        auto dijkstra = [&](int u) {
            memset(dist, 0x3f, sizeof(dist));
            memset(vis, 0, sizeof(vis));
            dist[u] = 0;
            for (int i = 0; i < n; ++i) {
                int k = -1;
                for (int j = 0; j < n; ++j) {
                    if (!vis[j] && (k == -1 || dist[j] < dist[k])) {
                        k = j;
                    }
                }
                vis[k] = true;
                for (int j = 0; j < n; ++j) {
                    dist[j] = min(dist[j], dist[k] + g[k][j]);
                }
            }
            return count_if(dist, dist + n, [&](int d) { return d <= distanceThreshold; });
        };
        int ans = n, cnt = n + 1;
        for (int i = n - 1; ~i; --i) {
            int t = dijkstra(i);
            if (t < cnt) {
                cnt = t;
                ans = i;
            }
        }
        return ans;
    }
};