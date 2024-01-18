class Solution {
public:
    int maximizeTheProfit(int n, vector<vector<int>>& offers) {
        sort(offers.begin(), offers.end(), [](const vector<int>& a, const vector<int>& b) {
            return a[1] < b[1];
        });
        n = offers.size();
        vector<int> f(n + 1);
        vector<int> g;
        for (auto& o : offers) {
            g.push_back(o[1]);
        }
        for (int i = 1; i <= n; ++i) {
            auto o = offers[i - 1];
            int j = lower_bound(g.begin(), g.end(), o[0]) - g.begin();
            f[i] = max(f[i - 1], f[j] + o[2]);
        }
        return f[n];
    }
};