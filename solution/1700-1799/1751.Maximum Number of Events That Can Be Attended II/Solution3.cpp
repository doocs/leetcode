class Solution {
public:
    int maxValue(vector<vector<int>>& events, int k) {
        ranges::sort(events);
        int n = events.size();
        vector<vector<int>> f(n + 1, vector<int>(k + 1));
        for (int i = n - 1; i >= 0; --i) {
            int ed = events[i][1], val = events[i][2];
            vector<int> t = {ed};
            int p = upper_bound(events.begin() + i + 1, events.end(), t, [](const auto& a, const auto& b) { return a[0] < b[0]; }) - events.begin();
            for (int c = 0; c <= k; ++c) {
                f[i][c] = f[i + 1][c];
                if (c) {
                    f[i][c] = max(f[i][c], f[p][c - 1] + val);
                }
            }
        }
        return f[0][k];
    }
};
