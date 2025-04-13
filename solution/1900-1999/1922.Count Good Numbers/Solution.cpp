class Solution {
public:
    int countGoodNumbers(long long n) {
        const int mod = 1e9 + 7;
        auto qpow = [](long long x, long long n) -> long long {
            long long res = 1;
            while (n) {
                if ((n & 1) == 1) {
                    res = res * x % mod;
                }
                x = x * x % mod;
                n >>= 1;
            }
            return res;
        };
        return qpow(5, (n + 1) >> 1) * qpow(4, n >> 1) % mod;
    }
};