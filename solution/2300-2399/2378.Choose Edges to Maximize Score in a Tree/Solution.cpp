class Solution {
public:
    long long maxScore(vector<vector<int>>& edges) {
        int n = edges.size();
        vector<vector<pair<int, int>>> g(n);
        for (int i = 1; i < n; ++i) {
            int p = edges[i][0], w = edges[i][1];
            g[p].emplace_back(i, w);
        }
        using ll = long long;
        using pll = pair<ll, ll>;
        function<pll(int)> dfs = [&](int i) -> pll {
            ll a = 0, b = 0, t = 0;
            for (auto& [j, w] : g[i]) {
                auto [x, y] = dfs(j);
                a += y;
                b += y;
                t = max(t, x - y + w);
            }
            b += t;
            return make_pair(a, b);
        };
        return dfs(0).second;
    }
};