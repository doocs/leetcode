class Solution {
public:
    int minimumDistance(int n, vector<vector<int>>& edges, int s, vector<int>& marked) {
        const int inf = 1 << 29;
        vector<vector<int>> g(n, vector<int>(n, inf));
        vector<int> dist(n, inf);
        dist[s] = 0;
        vector<bool> vis(n);
        for (auto& e : edges) {
            int u = e[0], v = e[1], w = e[2];
            g[u][v] = min(g[u][v], w);
        }
        for (int i = 0; i < n; ++i) {
            int t = -1;
            for (int j = 0; j < n; ++j) {
                if (!vis[j] && (t == -1 || dist[t] > dist[j])) {
                    t = j;
                }
            }
            vis[t] = true;
            for (int j = 0; j < n; ++j) {
                dist[j] = min(dist[j], dist[t] + g[t][j]);
            }
        }
        int ans = inf;
        for (int i : marked) {
            ans = min(ans, dist[i]);
        }
        return ans >= inf ? -1 : ans;
    }
};