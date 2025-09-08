class Solution {
public:
    int peopleAwareOfSecret(int n, int delay, int forget) {
        const int mod = 1e9 + 7;
        int m = (n << 1) + 10;
        vector<long long> d(m), cnt(m);
        cnt[1] = 1;
        for (int i = 1; i <= n; i++) {
            if (cnt[i]) {
                d[i] = (d[i] + cnt[i]) % mod;
                d[i + forget] = (d[i + forget] - cnt[i] + mod) % mod;
                int nxt = i + delay;
                while (nxt < i + forget) {
                    cnt[nxt] = (cnt[nxt] + cnt[i]) % mod;
                    nxt++;
                }
            }
        }
        long long ans = 0;
        for (int i = 0; i <= n; i++) {
            ans += d[i];
        }
        return ans % mod;
    }
};
