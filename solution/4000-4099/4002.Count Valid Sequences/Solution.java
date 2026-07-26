class Solution {
    static final int MX = 500001;
    static final long MOD = 1000000007L;
    static long[] f = new long[MX];
    static long[] g = new long[MX];

    static {
        f[0] = 1;
        g[0] = 1;
        for (int i = 1; i < MX; i++) {
            f[i] = f[i - 1] * i % MOD;
            g[i] = pow(f[i], MOD - 2);
        }
    }

    static long pow(long a, long b) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                res = res * a % MOD;
            }
            a = a * a % MOD;
            b >>= 1;
        }
        return res;
    }

    static long comb(int n, int k) {
        return f[n] * g[k] % MOD * g[n - k] % MOD;
    }

    public int countValidSequences(int n, int k) {
        long ans = comb(n - 1, k - 1);
        if ((n + k) % 2 == 0) {
            ans = (ans - comb((n + k) / 2 - 1, k - 1) + MOD) % MOD;
        }
        return (int) ans;
    }
}