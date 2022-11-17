class Solution {
public:
    double minmaxGasDist(vector<int>& stations, int k) {
        double left = 0, right = 1e8;
        auto check = [&](double x) {
            int s = 0;
            for (int i = 0; i < stations.size() - 1; ++i) {
                s += (int) ((stations[i + 1] - stations[i]) / x);
            }
            return s <= k;
        };
        while (right - left > 1e-6) {
            double mid = (left + right) / 2.0;
            if (check(mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return left;
    }
};