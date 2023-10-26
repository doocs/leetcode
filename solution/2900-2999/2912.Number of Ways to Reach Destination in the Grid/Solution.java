class Solution {
    public int numberOfWays(int n, int m, int k, int[] source, int[] dest) {
        final int mod = 1000000007;
        long[] f = new long[4];
        f[0] = 1;
        while (k-- > 0) {
            long[] g = new long[4];
            g[0] = ((n - 1) * f[1] + (m - 1) * f[2]) % mod;
            g[1] = (f[0] + (n - 2) * f[1] + (m - 1) * f[3]) % mod;
            g[2] = (f[0] + (m - 2) * f[2] + (n - 1) * f[3]) % mod;
            g[3] = (f[1] + f[2] + (n - 2) * f[3] + (m - 2) * f[3]) % mod;
            f = g;
        }
        if (source[0] == dest[0]) {
            return source[1] == dest[1] ? (int) f[0] : (int) f[2];
        }
        return source[1] == dest[1] ? (int) f[1] : (int) f[3];
    }
}