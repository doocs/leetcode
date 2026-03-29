class Solution {
    private static final int N = 100001;
    private static final int MOD = (int) 1e9 + 7;
    private static final long[] F = new long[N];
    private static final long[] G = new long[N];

    static {
        F[0] = 1;
        G[0] = 1;
        for (int i = 1; i < N; ++i) {
            F[i] = F[i - 1] * i % MOD;
            G[i] = qmi(F[i], MOD - 2, MOD);
        }
    }

    public static long qmi(long a, long k, long p) {
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

    public static long comb(int n, int k) {
        return (F[n] * G[k] % MOD) * G[n - k] % MOD;
    }

    public int countVisiblePeople(int n, int pos, int k) {
        int l = pos, r = n - pos - 1;
        long ans = 0;

        for (int a = 0; a <= Math.min(k, l); ++a) {
            int b = k - a;
            if (b <= r) {
                ans = (ans + 2 * comb(l, a) % MOD * comb(r, b) % MOD) % MOD;
            }
        }
        return (int) ans;
    }
}
