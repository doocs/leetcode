class Solution {
public:
    int minSpeedOnTime(vector<int>& dist, double hour) {
        if (dist.size() > ceil(hour)) {
            return -1;
        }
        const int m = 1e7;
        int l = 1, r = m + 1;
        int n = dist.size();
        auto check = [&](int v) {
            double s = 0;
            for (int i = 0; i < n; ++i) {
                double t = dist[i] * 1.0 / v;
                s += i == n - 1 ? t : ceil(t);
            }
            return s <= hour;
        };
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l > m ? -1 : l;
    }
};
