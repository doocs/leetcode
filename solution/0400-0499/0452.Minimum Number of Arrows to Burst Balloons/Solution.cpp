class Solution {
public:
    int findMinArrowShots(vector<vector<int>>& points) {
        sort(points.begin(), points.end(), [](const vector<int>& a, const vector<int>& b) {
            return a[1] < b[1];
        });
        int ans = 1;
        int x = points[0][1];
        for (auto& v : points) {
            int a = v[0], b = v[1];
            if (a > x) {
                ++ans;
                x = b;
            }
        }
        return ans;
    }
};