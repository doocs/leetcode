class Solution {
public:
    long long maxTaxiEarnings(int n, vector<vector<int>>& rides) {
        sort(rides.begin(), rides.end());
        int m = rides.size();
        vector<long long> f(m + 1);
        for (int i = m - 1; i >= 0; --i) {
            int st = rides[i][0], ed = rides[i][1], tip = rides[i][2];
            int j = lower_bound(rides.begin() + i + 1, rides.end(), ed, [](auto& a, int val) { return a[0] < val; }) - rides.begin();
            f[i] = max(f[i + 1], f[j] + ed - st + tip);
        }
        return f[0];
    }
};
