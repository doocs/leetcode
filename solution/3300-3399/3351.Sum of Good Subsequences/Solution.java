class Solution {
    public int sumOfGoodSubsequences(int[] nums) {
        final int mod = (int) 1e9 + 7;
        int mx = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
        }
        long[] f = new long[mx + 1];
        long[] g = new long[mx + 1];
        for (int x : nums) {
            f[x] += x;
            g[x] += 1;
            if (x > 0) {
                f[x] = (f[x] + f[x - 1] + g[x - 1] * x % mod) % mod;
                g[x] = (g[x] + g[x - 1]) % mod;
            }
            if (x + 1 <= mx) {
                f[x] = (f[x] + f[x + 1] + g[x + 1] * x % mod) % mod;
                g[x] = (g[x] + g[x + 1]) % mod;
            }
        }
        long ans = 0;
        for (long x : f) {
            ans = (ans + x) % mod;
        }
        return (int) ans;
    }
}
