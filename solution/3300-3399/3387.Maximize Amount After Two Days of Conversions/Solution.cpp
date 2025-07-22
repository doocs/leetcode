class Solution {
public:
    double maxAmount(string initialCurrency, vector<vector<string>>& pairs1, vector<double>& rates1, vector<vector<string>>& pairs2, vector<double>& rates2) {
        unordered_map<string, double> d1 = build(pairs1, rates1, initialCurrency);
        unordered_map<string, double> d2 = build(pairs2, rates2, initialCurrency);
        double ans = 0;
        for (const auto& [currency, rate] : d2) {
            if (d1.find(currency) != d1.end()) {
                ans = max(ans, d1[currency] / rate);
            }
        }
        return ans;
    }

private:
    unordered_map<string, double> build(vector<vector<string>>& pairs, vector<double>& rates, const string& init) {
        unordered_map<string, vector<pair<string, double>>> g;
        unordered_map<string, double> d;
        for (int i = 0; i < pairs.size(); ++i) {
            const string& a = pairs[i][0];
            const string& b = pairs[i][1];
            double r = rates[i];
            g[a].push_back({b, r});
            g[b].push_back({a, 1 / r});
        }

        auto dfs = [&](this auto&& dfs, const string& a, double v) -> void {
            if (d.find(a) != d.end()) {
                return;
            }

            d[a] = v;
            for (const auto& [b, r] : g[a]) {
                if (d.find(b) == d.end()) {
                    dfs(b, v * r);
                }
            }
        };
        dfs(init, 1.0);
        return d;
    }
};
