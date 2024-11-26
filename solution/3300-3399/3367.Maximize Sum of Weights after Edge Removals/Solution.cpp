class Solution {
public:
    long long maximizeSumOfWeights(vector<vector<int>>& edges, int k) {
        int n = edges.size() + 1;
        vector<vector<pair<int, int>>> g(n);
        for (auto& e : edges) {
            int u = e[0], v = e[1], w = e[2];
            g[u].emplace_back(v, w);
            g[v].emplace_back(u, w);
        }
        using ll = long long;
        auto dfs = [&](auto&& dfs, int u, int fa) -> pair<ll, ll> {
            ll s = 0;
            vector<ll> t;
            for (auto& [v, w] : g[u]) {
                if (v == fa) {
                    continue;
                }
                auto [a, b] = dfs(dfs, v, u);
                s += a;
                ll d = w + b - a;
                if (d > 0) {
                    t.push_back(d);
                }
            }
            ranges::sort(t, greater<>());
            for (int i = 0; i < min((int) t.size(), k - 1); ++i) {
                s += t[i];
            }
            return {s + (t.size() >= k ? t[k - 1] : 0), s};
        };

        auto [x, y] = dfs(dfs, 0, -1);
        return max(x, y);
    }
};
