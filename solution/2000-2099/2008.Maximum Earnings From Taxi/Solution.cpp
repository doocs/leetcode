class Solution {
public:
    long long maxTaxiEarnings(int n, vector<vector<int>>& rides) {
        sort(rides.begin(), rides.end());
        int m = rides.size();
        long long f[m];
        memset(f, -1, sizeof(f));
        function<long long(int)> dfs = [&](int i) -> long long {
            if (i >= m) {
                return 0;
            }
            if (f[i] != -1) {
                return f[i];
            }
            auto& r = rides[i];
            int st = r[0], ed = r[1], tip = r[2];
            int j = lower_bound(rides.begin() + i + 1, rides.end(), ed, [](auto& a, int val) { return a[0] < val; }) - rides.begin();
            return f[i] = max(dfs(i + 1), dfs(j) + ed - st + tip);
        };
        return dfs(0);
    }
};