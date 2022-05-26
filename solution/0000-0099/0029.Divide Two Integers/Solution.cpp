class Solution {
public:
    int divide(int a, int b) {
        int sign = 1;
        if (a < 0 ^ b < 0) {
            sign = -1;
        }

        auto x = abs(static_cast<long long>(a));
        auto y = abs(static_cast<long long>(b));
        auto tot = 0ll;
        while (x >= y) {
            int cnt = 0;
            while (x >= (y << (cnt + 1))) {
                ++cnt;
            }
            tot += 1ll << cnt;
            x -= y << cnt;
        }

        auto ans = sign * tot;
        if (ans >= INT32_MIN && ans <= INT32_MAX) {
            return static_cast<int>(ans);
        }
        return INT32_MAX;
    }
};
