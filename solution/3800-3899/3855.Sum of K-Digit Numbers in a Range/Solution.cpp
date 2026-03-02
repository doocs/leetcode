class Solution {
public:
    int sumOfNumbers(int l, int r, int k) {
        const int mod = 1'000'000'007;

        long long n = 1LL * r - l + 1;

        // ((l + r) * (r - l + 1) / 2) % mod
        long long sum = 1LL * (l + r) * n / 2 % mod;

        // pow(r - l + 1, k - 1, mod)
        long long part1 = qpow(n % mod, k - 1, mod);

        // (pow(10, k, mod) - 1)
        long long part2 = (qpow(10, k, mod) - 1 + mod) % mod;

        // Fermat inverse of 9
        long long inv9 = qpow(9, mod - 2, mod);

        long long ans = sum;
        ans = ans * part1 % mod;
        ans = ans * part2 % mod;
        ans = ans * inv9 % mod;

        return (int) ans;
    }

private:
    long long qpow(long long a, long long n, int mod) {
        long long ans = 1;
        a %= mod;
        while (n > 0) {
            if (n & 1) {
                ans = ans * a % mod;
            }
            a = a * a % mod;
            n >>= 1;
        }
        return ans;
    }
};
