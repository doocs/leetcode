class Solution {
public:
    int networkDelayTime(vector<vector<int>>& times, int n, int k) {
        const int inf = 1 << 29;
        vector<vector<int>> g(n, vector<int>(n, inf));
        for (const auto& e : times) {
            g[e[0] - 1][e[1] - 1] = e[2];
        }
        vector<int> dist(n, inf);
        dist[k - 1] = 0;
        vector<bool> vis(n);
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
        int ans = ranges::max(dist);
        return ans == inf ? -1 : ans;
    }
};
