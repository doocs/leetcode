class Solution {
    public int maximumXorProduct(long a, long b, int n) {
        final int mod = (int) 1e9 + 7;
        long ax = (a >> n) << n;
        long bx = (b >> n) << n;
        for (int i = n - 1; i >= 0; --i) {
            long x = a >> i & 1;
            long y = b >> i & 1;
            if (x == y) {
                ax |= 1L << i;
                bx |= 1L << i;
            } else if (ax < bx) {
                ax |= 1L << i;
            } else {
                bx |= 1L << i;
            }
        }
        ax %= mod;
        bx %= mod;
        return (int) (ax * bx % mod);
    }
}