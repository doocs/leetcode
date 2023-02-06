class Solution {
    public int maxNiceDivisors(int primeFactors) {
        if (primeFactors < 4) {
            return primeFactors;
        }
        final int mod = (int) 1e9 + 7;
        if (primeFactors % 3 == 0) {
            return (int) qmi(3, primeFactors / 3, mod);
        }
        if (primeFactors % 3 == 1) {
            return (int) (4 * qmi(3, primeFactors / 3 - 1, mod) % mod);
        }
        return (int) (2 * qmi(3, primeFactors / 3, mod) % mod);
    }

    private long qmi(long a, long k, long p) {
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
}