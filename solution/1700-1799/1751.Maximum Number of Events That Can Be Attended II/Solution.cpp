class Solution {
public:
    int maxValue(vector<vector<int>>& events, int k) {
        sort(events.begin(), events.end(), [&](auto& l, auto& r) -> bool { return l[1] < r[1]; });
        int n = events.size();
        vector<vector<int>> dp(n + 1, vector<int>(k + 1));
        for (int i = 0; i < n; ++i) {
            int s = events[i][0], v = events[i][2];
            int h = lower_bound(events.begin(), events.begin() + i, s, [](auto& e, int x) { return e[1] < x; }) - events.begin();
            for (int j = 1; j <= k; ++j) {
                dp[i + 1][j] = max(dp[i][j], dp[h][j - 1] + v);
            }
        }
        return dp[n][k];
    }
};