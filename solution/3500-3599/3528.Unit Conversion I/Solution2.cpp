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
        vector<pair<int, long long>> stk{{0, 1}};
        while (!stk.empty()) {
            auto [s, mul] = stk.back();
            stk.pop_back();
            ans[s] = mul;
            for (auto [t, w] : g[s]) {
                stk.push_back({t, mul * w % mod});
            }
        }
        return ans;
    }
};
