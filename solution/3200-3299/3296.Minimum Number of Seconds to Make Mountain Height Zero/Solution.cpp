class Solution {
public:
    long long minNumberOfSeconds(int mountainHeight, vector<int>& workerTimes) {
        using ll = long long;
        ll l = 1, r = 1e16;
        auto check = [&](ll t) -> bool {
            ll h = 0;
            for (int& wt : workerTimes) {
                h += (long long) (sqrt(t * 2.0 / wt + 0.25) - 0.5);
            }
            return h >= mountainHeight;
        };
        while (l < r) {
            ll mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
};
