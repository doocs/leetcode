const int MX = 1e5 + 10;
const int MOD = 1e9 + 7;
long long f[MX];
long long g[MX];

long long qpow(long a, int k) {
    long res = 1;
    while (k != 0) {
        if ((k & 1) == 1) {
            res = res * a % MOD;
        }
        k >>= 1;
        a = a * a % MOD;
    }
    return res;
}

int init = []() {
    f[0] = g[0] = 1;
    for (int i = 1; i < MX; ++i) {
        f[i] = f[i - 1] * i % MOD;
        g[i] = qpow(f[i], MOD - 2);
    }
    return 0;
}();

long long comb(int m, int n) {
    return f[m] * g[n] % MOD * g[m - n] % MOD;
}

class Solution {
public:
    int countGoodArrays(int n, int m, int k) {
        return comb(n - 1, k) * m % MOD * qpow(m - 1, n - k - 1) % MOD;
    }
};