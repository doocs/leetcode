class Solution {
public:
    int minNonZeroProduct(int p) {
        const int mod = 1e9 + 7;
        long long a = ((1LL << p) - 1) % mod;
        long long b = qmi(((1LL << p) - 2) % mod, (1L << (p - 1)) - 1, mod);
        return a * b % mod;
    }

    long long qmi(long long a, long long k, int p) {
        long long res = 1;
        while (k != 0) {
            if ((k & 1) == 1) {
                res = res * a % p;
            }
            k >>= 1;
            a = a * a % p;
        }
        return res;
    }
};