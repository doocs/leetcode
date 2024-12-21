class Solution {
public:
    int maximalPathQuality(vector<int>& values, vector<vector<int>>& edges, int maxTime) {
        int n = values.size();
        vector<pair<int, int>> g[n];
        for (auto& e : edges) {
            int u = e[0], v = e[1], t = e[2];
            g[u].emplace_back(v, t);
            g[v].emplace_back(u, t);
        }
        bool vis[n];
        memset(vis, false, sizeof(vis));
        vis[0] = true;
        int ans = 0;
        auto dfs = [&](this auto&& dfs, int u, int cost, int value) -> void {
            if (u == 0) {
                ans = max(ans, value);
            }
            for (auto& [v, t] : g[u]) {
                if (cost + t <= maxTime) {
                    if (vis[v]) {
                        dfs(v, cost + t, value);
                    } else {
                        vis[v] = true;
                        dfs(v, cost + t, value + values[v]);
                        vis[v] = false;
                    }
                }
            }
        };
        dfs(0, 0, values[0]);
        return ans;
    }
};
