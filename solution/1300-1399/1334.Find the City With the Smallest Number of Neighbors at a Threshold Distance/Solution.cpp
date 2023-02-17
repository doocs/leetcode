class Solution {
public:
    int findTheCity(int n, vector<vector<int>>& edges, int distanceThreshold) {
        const int inf = 1e7;
        vector<vector<int>> g(n, vector<int>(n, inf));
        vector<int> dist(n, inf);
        vector<bool> vis(n);
        for (auto& e : edges) {
            int f = e[0], t = e[1], w = e[2];
            g[f][t] = g[t][f] = w;
        }
        auto dijkstra = [&](int u) {
            dist.assign(n, inf);
            vis.assign(n, false);
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
            int cnt = 0;
            for (int& d : dist) {
                cnt += d <= distanceThreshold;
            }
            return cnt;
        };
        int ans = n, t = inf;
        for (int i = n - 1; ~i; --i) {
            int cnt = dijkstra(i);
            if (t > cnt) {
                t = cnt;
                ans = i;
            }
        }
        return ans;
    }
};