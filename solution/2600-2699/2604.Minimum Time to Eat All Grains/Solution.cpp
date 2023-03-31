class Solution {
public:
    int minimumTime(vector<int>& hens, vector<int>& grains) {
        int m = grains.size();
        sort(hens.begin(), hens.end());
        sort(grains.begin(), grains.end());
        int l = 0;
        int r = abs(hens[0] - grains[0]) + grains[m - 1] - grains[0];
        auto check = [&](int t) -> bool {
            int j = 0;
            for (int x : hens) {
                if (j == m) {
                    return true;
                }
                int y = grains[j];
                if (y <= x) {
                    int d = x - y;
                    if (d > t) {
                        return false;
                    }
                    while (j < m && grains[j] <= x) {
                        ++j;
                    }
                    while (j < m && min(d, grains[j] - x) + grains[j] - y <= t) {
                        ++j;
                    }
                } else {
                    while (j < m && grains[j] - x <= t) {
                        ++j;
                    }
                }
            }
            return j == m;
        };
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
};