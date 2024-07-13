class Solution {
public:
    vector<vector<int>> kClosest(vector<vector<int>>& points, int k) {
        sort(points.begin(), points.end(), [](const vector<int>& p1, const vector<int>& p2) {
            return hypot(p1[0], p1[1]) < hypot(p2[0], p2[1]);
        });
        return vector<vector<int>>(points.begin(), points.begin() + k);
    }
};
