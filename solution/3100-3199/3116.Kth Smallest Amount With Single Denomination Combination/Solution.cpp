class Solution {
public:
    long long findKthSmallest(vector<int>& coins, int k) {
        using ll = long long;
        ll l = 1, r = 1e11;
        int n = coins.size();

        auto check = [&](ll mx) {
            ll cnt = 0;
            for (int i = 1; i < 1 << n; ++i) {
                ll v = 1;
                for (int j = 0; j < n; ++j) {
                    if (i >> j & 1) {
                        v = lcm(v, coins[j]);
                        if (v > mx) {
                            break;
                        }
                    }
                }
                int m = __builtin_popcount(i);
                if (m & 1) {
                    cnt += mx / v;
                } else {
                    cnt -= mx / v;
                }
            }
            return cnt >= k;
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