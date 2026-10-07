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
        vector<ll> keep(n), reserve(n);
        vector<array<int, 3>> stk{{0, -1, 0}};
        while (!stk.empty()) {
            auto [u, fa, state] = stk.back();
            stk.pop_back();
            if (state == 0) {
                stk.push_back({u, fa, 1});
                for (auto& [v, w] : g[u]) {
                    if (v != fa) {
                        stk.push_back({v, u, 0});
                    }
                }
            } else {
                ll s = 0;
                vector<ll> t;
                for (auto& [v, w] : g[u]) {
                    if (v == fa) {
                        continue;
                    }
                    s += keep[v];
                    ll d = w + reserve[v] - keep[v];
                    if (d > 0) {
                        t.push_back(d);
                    }
                }
                ranges::sort(t, greater<>());
                for (int i = 0; i < min((int) t.size(), k - 1); ++i) {
                    s += t[i];
                }
                reserve[u] = s;
                keep[u] = s + (t.size() >= (size_t) k ? t[k - 1] : 0);
            }
        }
        return max(keep[0], reserve[0]);
    }
};
