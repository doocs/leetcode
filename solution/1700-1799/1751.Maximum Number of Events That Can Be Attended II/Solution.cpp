class Solution {
public:
    int maxValue(vector<vector<int>>& events, int k) {
        sort(events.begin(), events.end());
        int n = events.size();
        int f[n][k + 1];
        memset(f, 0, sizeof(f));
        function<int(int, int)> dfs = [&](int i, int k) -> int {
            if (i >= n || k <= 0) {
                return 0;
            }
            if (f[i][k] > 0) {
                return f[i][k];
            }
            int ed = events[i][1], val = events[i][2];
            vector<int> t = {ed};
            int p = upper_bound(events.begin() + i + 1, events.end(), t, [](const auto& a, const auto& b) { return a[0] < b[0]; }) - events.begin();
            f[i][k] = max(dfs(i + 1, k), dfs(p, k - 1) + val);
            return f[i][k];
        };
        return dfs(0, k);
    }
};