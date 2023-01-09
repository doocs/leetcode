class Solution {
public:
    int maxNiceDivisors(int primeFactors) {
        if (primeFactors < 4) {
            return primeFactors;
        }
        const int mod = 1e9 + 7;
        if (primeFactors % 3 == 0) {
            return qmi(3, primeFactors / 3, mod);
        }
        if (primeFactors % 3 == 1) {
            return 4 * qmi(3, primeFactors / 3 - 1, mod) % mod;
        }
        return 2 * qmi(3, primeFactors / 3, mod) % mod;
    }

    long qmi(long a, long k, long p) {
        long res = 1;
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