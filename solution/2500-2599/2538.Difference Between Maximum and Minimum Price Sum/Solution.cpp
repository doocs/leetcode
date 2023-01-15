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
        using pll = pair<ll, ll>;
        ll ans = 0;
        function<pll(int, int)> dfs = [&](int i, int fa) {
            ll a = price[i], b = 0;
            for (int j : g[i]) {
                if (j != fa) {
                    auto [c, d] = dfs(j, i);
                    ans = max({ans, a + d, b + c});
                    a = max(a, price[i] + c);
                    b = max(b, price[i] + d);
                }
            }
            return pll{a, b};
        };
        dfs(0, -1);
        return ans;
    }
};
