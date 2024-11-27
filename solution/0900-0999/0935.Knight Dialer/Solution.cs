public class Solution {
    public int KnightDialer(int n) {
        const int mod = 1000000007;
        long[] f = new long[10];
        for (int i = 0; i < 10; i++) {
            f[i] = 1;
        }

        while (--n > 0) {
            long[] g = new long[10];
            g[0] = (f[4] + f[6]) % mod;
            g[1] = (f[6] + f[8]) % mod;
            g[2] = (f[7] + f[9]) % mod;
            g[3] = (f[4] + f[8]) % mod;
            g[4] = (f[0] + f[3] + f[9]) % mod;
            g[6] = (f[0] + f[1] + f[7]) % mod;
            g[7] = (f[2] + f[6]) % mod;
            g[8] = (f[1] + f[3]) % mod;
            g[9] = (f[2] + f[4]) % mod;
            f = g;
        }

        return (int)(f.Sum() % mod);
    }
}
