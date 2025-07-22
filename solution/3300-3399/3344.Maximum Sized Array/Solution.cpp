const int MX = 1330;
long long f[MX];
auto init = [] {
    f[0] = 0;
    for (int i = 1; i < MX; ++i) {
        f[i] = f[i - 1] + i;
        for (int j = 0; j < i; ++j) {
            f[i] += 2 * (i | j);
        }
    }
    return 0;
}();

class Solution {
public:
    int maxSizedArray(long long s) {
        int l = 1, r = MX;
        while (l < r) {
            int m = (l + r + 1) >> 1;
            if (f[m - 1] * (m - 1) * m / 2 <= s) {
                l = m;
            } else {
                r = m - 1;
            }
        }
        return l;
    }
};
