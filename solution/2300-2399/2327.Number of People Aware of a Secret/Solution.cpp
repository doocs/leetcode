using ll = long long;
const int mod = 1e9 + 7;

class Solution {
public:
    int peopleAwareOfSecret(int n, int delay, int forget) {
        int m = (n << 1) + 10;
        vector<ll> d(m);
        vector<ll> cnt(m);
        cnt[1] = 1;
        for (int i = 1; i <= n; ++i) {
            if (!cnt[i]) continue;
            d[i] = (d[i] + cnt[i]) % mod;
            d[i + forget] = (d[i + forget] - cnt[i] + mod) % mod;
            int nxt = i + delay;
            while (nxt < i + forget) {
                cnt[nxt] = (cnt[nxt] + cnt[i]) % mod;
                ++nxt;
            }
        }
        int ans = 0;
        for (int i = 1; i <= n; ++i) ans = (ans + d[i]) % mod;
        return ans;
    }
};