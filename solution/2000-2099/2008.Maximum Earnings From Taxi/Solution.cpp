class Solution {
public:
    long long maxTaxiEarnings(int n, vector<vector<int>>& rides) {
        sort(rides.begin(), rides.end(), [](const vector<int>& a, const vector<int>& b) { return a[1] < b[1]; });
        int m = rides.size();
        vector<long long> f(m + 1);
        for (int i = 1; i <= m; ++i) {
            auto& r = rides[i - 1];
            int st = r[0], ed = r[1], tip = r[2];
            auto it = lower_bound(rides.begin(), rides.begin() + i, st + 1, [](auto& a, int val) { return a[1] < val; });
            int j = distance(rides.begin(), it);
            f[i] = max(f[i - 1], f[j] + ed - st + tip);
        }
        return f.back();
    }
};