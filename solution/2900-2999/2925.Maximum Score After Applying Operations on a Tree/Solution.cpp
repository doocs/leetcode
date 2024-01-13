class Solution {
public:
    long long maximumScoreAfterOperations(vector<vector<int>>& edges, vector<int>& values) {
        int n = values.size();
        vector<int> g[n];
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].emplace_back(b);
            g[b].emplace_back(a);
        }
        using ll = long long;
        function<pair<ll, ll>(int, int)> dfs = [&](int i, int fa) -> pair<ll, ll> {
            ll a = 0, b = 0;
            bool leaf = true;
            for (int j : g[i]) {
                if (j != fa) {
                    auto [aa, bb] = dfs(j, i);
                    a += aa;
                    b += bb;
                    leaf = false;
                }
            }
            if (leaf) {
                return {values[i], 0LL};
            }
            return {values[i] + a, max(values[i] + b, a)};
        };
        auto [_, b] = dfs(0, -1);
        return b;
    }
};