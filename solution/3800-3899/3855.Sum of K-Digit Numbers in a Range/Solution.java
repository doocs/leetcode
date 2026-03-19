class Solution {
    public int sumOfNumbers(int l, int r, int k) {
        final int mod = 1_000_000_007;

        long n = r - l + 1L;

        // ((l + r) * (r - l + 1) // 2) % mod
        long sum = (long) (l + r) * n / 2 % mod;

        // pow(r - l + 1, k - 1, mod)
        long part1 = qpow(n % mod, k - 1, mod);

        // (pow(10, k, mod) - 1)
        long part2 = (qpow(10, k, mod) - 1 + mod) % mod;

        // pow(9, mod - 2, mod)  (Fermat inverse of 9)
        long inv9 = qpow(9, mod - 2, mod);

        long ans = sum;
        ans = ans * part1 % mod;
        ans = ans * part2 % mod;
        ans = ans * inv9 % mod;

        return (int) ans;
    }

    private int qpow(long a, int n, int mod) {
        long ans = 1;
        a %= mod;
        for (; n > 0; n >>= 1) {
            if ((n & 1) == 1) {
                ans = ans * a % mod;
            }
            a = a * a % mod;
        }
        return (int) ans;
    }
}
