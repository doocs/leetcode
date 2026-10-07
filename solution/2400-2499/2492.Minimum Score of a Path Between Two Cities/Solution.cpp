class Solution {
public:
    int minScore(int n, vector<vector<int>>& roads) {
        vector<vector<pair<int, int>>> g(n + 1);
        for (auto& e : roads) {
            int a = e[0], b = e[1], w = e[2];
            g[a].push_back({b, w});
            g[b].push_back({a, w});
        }

        vector<bool> vis(n + 1, false);
        int ans = INT_MAX;

        auto dfs = [&](this auto&& dfs, int a) -> void {
            vis[a] = true;
            for (auto& [b, w] : g[a]) {
                ans = min(ans, w);
                if (!vis[b]) {
                    dfs(b);
                }
            }
        };

        dfs(1);
        return ans;
    }
};
