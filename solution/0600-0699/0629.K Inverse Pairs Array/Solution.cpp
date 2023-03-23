class Solution {
public:
    int kInversePairs(int n, int k) {
        int f[k + 1];
        int s[k + 2];
        memset(f, 0, sizeof(f));
        f[0] = 1;
        fill(s, s + k + 2, 1);
        s[0] = 0;
        const int mod = 1e9 + 7;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= k; ++j) {
                f[j] = (s[j + 1] - s[max(0, j - (i - 1))] + mod) % mod;
            }
            for (int j = 1; j <= k + 1; ++j) {
                s[j] = (s[j - 1] + f[j - 1]) % mod;
            }
        }
        return f[k];
    }
};