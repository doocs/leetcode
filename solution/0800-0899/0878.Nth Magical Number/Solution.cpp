using ll = long long;

class Solution {
public:
    const int mod = 1e9 + 7;

    int nthMagicalNumber(int n, int a, int b) {
        int c = lcm(a, b);
        ll l = 0, r = 1ll * (a + b) * n;
        while (l < r) {
            ll mid = l + r >> 1;
            if (mid / a + mid / b - mid / c >= n)
                r = mid;
            else
                l = mid + 1;
        }
        return l % mod;
    }
};