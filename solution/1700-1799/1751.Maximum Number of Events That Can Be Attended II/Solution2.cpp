class Solution {
public:
    int maxValue(vector<vector<int>>& events, int k) {
        sort(events.begin(), events.end(), [](const auto& a, const auto& b) { return a[1] < b[1]; });
        int n = events.size();
        int f[n + 1][k + 1];
        memset(f, 0, sizeof(f));
        for (int i = 1; i <= n; ++i) {
            int st = events[i - 1][0], val = events[i - 1][2];
            vector<int> t = {st};
            int p = lower_bound(events.begin(), events.begin() + i - 1, t, [](const auto& a, const auto& b) { return a[1] < b[0]; }) - events.begin();
            for (int j = 1; j <= k; ++j) {
                f[i][j] = max(f[i - 1][j], f[p][j - 1] + val);
            }
        }
        return f[n][k];
    }
};