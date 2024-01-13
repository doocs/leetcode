class Solution {
public:
    int cuttingRope(int n) {
        if (n < 4) {
            return n - 1;
        }
        const int mod = 1e9 + 7;
        auto qpow = [&](long long a, long long n) {
            long long ans = 1;
            for (; n; n >>= 1) {
                if (n & 1) {
                    ans = ans * a % mod;
                }
                a = a * a % mod;
            }
            return (int) ans;
        };
        if (n % 3 == 0) {
            return qpow(3, n / 3);
        }
        if (n % 3 == 1) {
            return qpow(3, n / 3 - 1) * 4L % mod;
        }
        return qpow(3, n / 3) * 2 % mod;
    }
};