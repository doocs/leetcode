class Solution {
    public int cuttingRope(int n) {
        if (n < 4) {
            return n - 1;
        }
        final int mod = (int) 1e9 + 7;
        if (n % 3 == 0) {
            return (int) qmi(3, n / 3, mod);
        }
        if (n % 3 == 1) {
            return (int) (qmi(3, n / 3 - 1, mod) * 4 % mod);
        }
        return (int) (qmi(3, n / 3, mod) * 2 % mod);
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
}