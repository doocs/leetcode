class Solution {
    public int minNonZeroProduct(int p) {
        final int mod = (int) 1e9 + 7;
        long a = ((1L << p) - 1) % mod;
        long b = qmi(((1L << p) - 2) % mod, (1L << (p - 1)) - 1, mod);
        return (int) (a * b % mod);
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