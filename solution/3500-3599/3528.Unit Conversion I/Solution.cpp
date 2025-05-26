class Solution {
public:
    vector<int> baseUnitConversions(vector<vector<int>>& conversions) {
        const int mod = 1e9 + 7;
        int n = conversions.size() + 1;
        vector<vector<pair<int, int>>> g(n);
        vector<int> ans(n);
        for (const auto& e : conversions) {
            g[e[0]].push_back({e[1], e[2]});
        }
        auto dfs = [&](this auto&& dfs, int s, long long mul) -> void {
            ans[s] = mul;
            for (auto [t, w] : g[s]) {
                dfs(t, mul * w % mod);
            }
        };
        dfs(0, 1);
        return ans;
    }
};