class Solution {
public:
    vector<int> countRectangles(vector<vector<int>>& rectangles, vector<vector<int>>& points) {
        int n = 101;
        vector<vector<int>> d(n);
        for (auto& r : rectangles) d[r[1]].push_back(r[0]);
        for (auto& v : d) sort(v.begin(), v.end());
        vector<int> ans;
        for (auto& p : points) {
            int x = p[0], y = p[1];
            int cnt = 0;
            for (int h = y; h < n; ++h) {
                auto& xs = d[h];
                cnt += xs.size() - (lower_bound(xs.begin(), xs.end(), x) - xs.begin());
            }
            ans.push_back(cnt);
        }
        return ans;
    }
};