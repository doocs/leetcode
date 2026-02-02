constexpr int MX = 50;
long long c[MX][MX + 1];

auto init = [] {
    for (int i = 0; i < MX; i++) {
        c[i][0] = 1;
        for (int j = 1; j <= i; j++) {
            c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
        }
    }
    return 0;
}();

class Solution {
public:
    long long nthSmallest(long long n, int k) {
        long long ans = 0;
        for (int i = 49; i >= 0; i--) {
            if (n > c[i][k]) {
                n -= c[i][k];
                ans |= 1LL << i;
                if (--k == 0) {
                    break;
                }
            }
        }
        return ans;
    }
};
