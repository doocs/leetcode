class Solution {
    private static final int N = 10001;
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

    public int countGoodSubsequences(String s) {
        int[] cnt = new int[26];
        int mx = 1;
        for (int i = 0; i < s.length(); ++i) {
            mx = Math.max(mx, ++cnt[s.charAt(i) - 'a']);
        }
        long ans = 0;
        for (int i = 1; i <= mx; ++i) {
            long x = 1;
            for (int j = 0; j < 26; ++j) {
                if (cnt[j] >= i) {
                    x = x * (comb(cnt[j], i) + 1) % MOD;
                }
            }
            ans = (ans + x - 1) % MOD;
        }
        return (int) ans;
    }
}