class Solution {
public:
    long long maximumScoreAfterOperations(vector<vector<int>>& edges, vector<int>& values) {
        int n = values.size();
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].emplace_back(b);
            g[b].emplace_back(a);
        }
        using ll = long long;
        vector<pair<ll, ll>> sub(n);
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
                ll a = 0, b = 0;
                bool leaf = true;
                for (int j : g[i]) {
                    if (j != fa) {
                        leaf = false;
                        a += sub[j].first;
                        b += sub[j].second;
                    }
                }
                if (leaf) {
                    sub[i] = {values[i], 0};
                } else {
                    sub[i] = {values[i] + a, max(values[i] + b, a)};
                }
            }
        }
        return sub[0].second;
    }
};
