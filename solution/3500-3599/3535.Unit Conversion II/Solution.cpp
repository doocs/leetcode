class Solution {
public:
    vector<int> queryConversions(vector<vector<int>>& conversions, vector<vector<int>>& queries) {
        const int mod = 1e9 + 7;
        int n = conversions.size() + 1;
        vector<vector<pair<int, int>>> g(n);
        for (auto& e : conversions) {
            g[e[0]].emplace_back(e[1], e[2]);
        }

        vector<int> res(n);

        auto dfs = [&](this auto&& dfs, int s, long long mul) -> void {
            res[s] = mul;
            for (auto [t, w] : g[s]) {
                dfs(t, mul * w % mod);
            }
        };
        dfs(0, 1);

        auto qpow = [&](long long x, int n) {
            long long res = 1;
            while (n) {
                if (n & 1) {
                    res = res * x % mod;
                }
                x = x * x % mod;
                n >>= 1;
            }
            return res;
        };

        vector<int> ans;
        for (auto& q : queries) {
            ans.push_back(res[q[1]] * qpow(res[q[0]], mod - 2) % mod);
        }
        return ans;
    }
};