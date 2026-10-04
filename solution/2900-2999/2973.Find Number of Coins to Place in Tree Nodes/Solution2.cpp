class Solution {
public:
    vector<long long> placedCoins(vector<vector<int>>& edges, vector<int>& cost) {
        int n = cost.size();
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        vector<long long> ans(n, 1);
        vector<vector<int>> sub(n);
        vector<array<int, 3>> stk{{0, -1, 0}};
        while (!stk.empty()) {
            auto [a, fa, state] = stk.back();
            stk.pop_back();
            if (state == 0) {
                stk.push_back({a, fa, 1});
                for (int b : g[a]) {
                    if (b != fa) {
                        stk.push_back({b, a, 0});
                    }
                }
            } else {
                vector<int> res = {cost[a]};
                for (int b : g[a]) {
                    if (b != fa) {
                        res.insert(res.end(), sub[b].begin(), sub[b].end());
                    }
                }
                sort(res.begin(), res.end());
                int m = res.size();
                if (m >= 3) {
                    long long x = 1LL * res[m - 1] * res[m - 2] * res[m - 3];
                    long long y = 1LL * res[0] * res[1] * res[m - 1];
                    ans[a] = max({0LL, x, y});
                }
                if (m > 5) {
                    res = {res[0], res[1], res[m - 3], res[m - 2], res[m - 1]};
                }
                sub[a] = std::move(res);
            }
        }
        return ans;
    }
};
