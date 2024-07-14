class Solution {
public:
    int minRectanglesToCoverPoints(vector<vector<int>>& points, int w) {
        sort(points.begin(), points.end());
        int ans = 0, x1 = -(1 << 30);
        for (auto& p : points) {
            int x = p[0];
            if (x1 + w < x) {
                x1 = x;
                ++ans;
            }
        }
        return ans;
    }
};