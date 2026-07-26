const int MX = 500001;
const long long MOD = 1000000007LL;

long long f[MX];
long long g[MX];

long long qpow(long long a, long long b) {
    long long res = 1;
    while (b > 0) {
        if (b & 1) {
            res = res * a % MOD;
        }
        a = a * a % MOD;
        b >>= 1;
    }
    return res;
}

int init = []() {
    f[0] = 1;
    g[0] = 1;

    for (int i = 1; i < MX; i++) {
        f[i] = f[i - 1] * i % MOD;
        g[i] = qpow(f[i], MOD - 2);
    }

    return 0;
}();

long long comb(int n, int k) {
    return f[n] * g[k] % MOD * g[n - k] % MOD;
}

class Solution {
public:
    int countValidSequences(int n, int k) {
        long long ans = comb(n - 1, k - 1);

        if ((n + k) % 2 == 0) {
            ans = (ans - comb((n + k) / 2 - 1, k - 1) + MOD) % MOD;
        }

        return (int) ans;
    }
};