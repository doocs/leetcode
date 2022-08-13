class Solution {
public:
    int visiblePoints(vector<vector<int>>& points, int angle, vector<int>& location) {
        vector<double> v;
        int x = location[0], y = location[1];
        int same = 0;
        for (auto& p : points) {
            int xi = p[0], yi = p[1];
            if (xi == x && yi == y)
                ++same;
            else
                v.emplace_back(atan2(yi - y, xi - x));
        }
        sort(v.begin(), v.end());
        int n = v.size();
        for (int i = 0; i < n; ++i) v.emplace_back(v[i] + 2 * M_PI);

        int mx = 0;
        double t = angle * M_PI / 180;
        for (int i = 0, j = 0; j < 2 * n; ++j) {
            while (i < j && v[j] - v[i] > t) ++i;
            mx = max(mx, j - i + 1);
        }
        return mx + same;
    }
};