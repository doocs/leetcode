class Solution {
    public int squareFreeSubsets(int[] nums) {
        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
        int[] cnt = new int[31];
        for (int x : nums) {
            ++cnt[x];
        }
        final int mod = (int) 1e9 + 7;
        int n = primes.length;
        long[] f = new long[1 << n];
        f[0] = 1;
        for (int i = 0; i < cnt[1]; ++i) {
            f[0] = (f[0] * 2) % mod;
        }
        for (int x = 2; x < 31; ++x) {
            if (cnt[x] == 0 || x % 4 == 0 || x % 9 == 0 || x % 25 == 0) {
                continue;
            }
            int mask = 0;
            for (int i = 0; i < n; ++i) {
                if (x % primes[i] == 0) {
                    mask |= 1 << i;
                }
            }
            for (int state = (1 << n) - 1; state > 0; --state) {
                if ((state & mask) == mask) {
                    f[state] = (f[state] + cnt[x] * f[state ^ mask]) % mod;
                }
            }
        }
        long ans = 0;
        for (int i = 0; i < 1 << n; ++i) {
            ans = (ans + f[i]) % mod;
        }
        ans -= 1;
        return (int) ans;
    }
}