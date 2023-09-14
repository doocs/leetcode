class Solution {
    public int minNonZeroProduct(int p) {
        final int mod = (int) 1e9 + 7;
        long a = ((1L << p) - 1) % mod;
        long b = qpow(((1L << p) - 2) % mod, (1L << (p - 1)) - 1, mod);
        return (int) (a * b % mod);
    }

    private long qpow(long a, long n, int mod) {
        long ans = 1;
        for (; n > 0; n >>= 1) {
            if ((n & 1) == 1) {
                ans = ans * a % mod;
            }
            a = a * a % mod;
        }
        return ans;
    }
}