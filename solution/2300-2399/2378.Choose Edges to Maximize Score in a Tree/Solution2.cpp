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
        vector<pair<ll, ll>> down(n);
        vector<array<int, 2>> stk{{0, 0}};
        while (!stk.empty()) {
            auto [i, state] = stk.back();
            stk.pop_back();
            if (state == 0) {
                stk.push_back({i, 1});
                for (auto& [j, w] : g[i]) {
                    stk.push_back({j, 0});
                }
            } else {
                ll a = 0, b = 0, t = 0;
                for (auto& [j, w] : g[i]) {
                    auto [x, y] = down[j];
                    a += y;
                    b += y;
                    t = max(t, x - y + w);
                }
                b += t;
                down[i] = {a, b};
            }
        }
        return down[0].second;
    }
};
