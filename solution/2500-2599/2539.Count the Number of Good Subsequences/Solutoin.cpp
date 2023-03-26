int N = 10001;
int MOD = 1e9 + 7;
long f[10001];
long g[10001];

long qmi(long a, long k, long p) {
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

int init = []() {
    f[0] = 1;
    g[0] = 1;
    for (int i = 1; i < N; ++i) {
        f[i] = f[i - 1] * i % MOD;
        g[i] = qmi(f[i], MOD - 2, MOD);
    }
    return 0;
}();

int comb(int n, int k) {
    return (f[n] * g[k] % MOD) * g[n - k] % MOD;
}

class Solution {
public:
    int countGoodSubsequences(string s) {
        int cnt[26]{};
        int mx = 1;
        for (char& c : s) {
            mx = max(mx, ++cnt[c - 'a']);
        }
        long ans = 0;
        for (int i = 1; i <= mx; ++i) {
            long x = 1;
            for (int j = 0; j < 26; ++j) {
                if (cnt[j] >= i) {
                    x = (x * (comb(cnt[j], i) + 1)) % MOD;
                }
            }
            ans = (ans + x - 1) % MOD;
        }
        return ans;
    }
};