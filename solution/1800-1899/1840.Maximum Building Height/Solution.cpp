class Solution {
public:
    int maxBuilding(int n, vector<vector<int>>& restrictions) {
        auto&& r = restrictions;
        r.push_back({1, 0});
        sort(r.begin(), r.end());
        if (r[r.size() - 1][0] != n) r.push_back({n, n - 1});
        int m = r.size();
        for (int i = 1; i < m; ++i) {
            r[i][1] = min(r[i][1], r[i - 1][1] + r[i][0] - r[i - 1][0]);
        }
        for (int i = m - 2; i > 0; --i) {
            r[i][1] = min(r[i][1], r[i + 1][1] + r[i + 1][0] - r[i][0]);
        }
        int ans = 0;
        for (int i = 0; i < m - 1; ++i) {
            int t = (r[i][1] + r[i + 1][1] + r[i + 1][0] - r[i][0]) / 2;
            ans = max(ans, t);
        }
        return ans;
    }
};