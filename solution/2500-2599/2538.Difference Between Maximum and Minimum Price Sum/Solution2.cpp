class Solution {
public:
    long long maxOutput(int n, vector<vector<int>>& edges, vector<int>& price) {
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        using ll = long long;
        vector<pair<ll, ll>> down(n);
        ll ans = 0;
        vector<array<int, 3>> stk{{0, -1, 0}};
        while (!stk.empty()) {
            auto [i, fa, state] = stk.back();
            stk.pop_back();
            if (state == 0) {
                stk.push_back({i, fa, 1});
                for (int j : g[i]) {
                    if (j != fa) {
                        stk.push_back({j, i, 0});
                    }
                }
            } else {
                ll a = price[i], b = 0;
                for (int j : g[i]) {
                    if (j != fa) {
                        auto [c, d] = down[j];
                        ans = max({ans, a + d, b + c});
                        a = max(a, price[i] + c);
                        b = max(b, price[i] + d);
                    }
                }
                down[i] = {a, b};
            }
        }
        return ans;
    }
};
