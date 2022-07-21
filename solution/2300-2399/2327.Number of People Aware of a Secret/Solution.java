class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int peopleAwareOfSecret(int n, int delay, int forget) {
        int m = (n << 1) + 10;
        long[] d = new long[m];
        long[] cnt = new long[m];
        cnt[1] = 1;
        for (int i = 1; i <= n; ++i) {
            if (cnt[i] > 0) {
                d[i] = (d[i] + cnt[i]) % MOD;
                d[i + forget] = (d[i + forget] - cnt[i] + MOD) % MOD;
                int nxt = i + delay;
                while (nxt < i + forget) {
                    cnt[nxt] = (cnt[nxt] + cnt[i]) % MOD;
                    ++nxt;
                }
            }
        }
        long ans = 0;
        for (int i = 1; i <= n; ++i) {
            ans = (ans + d[i]) % MOD;
        }
        return (int) ans;
    }
}