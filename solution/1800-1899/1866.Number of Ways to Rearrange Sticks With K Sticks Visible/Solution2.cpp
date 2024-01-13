class Solution {
public:
    int rearrangeSticks(int n, int k) {
        const int mod = 1e9 + 7;
        int f[k + 1];
        memset(f, 0, sizeof(f));
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = k; j; --j) {
                f[j] = (f[j - 1] + f[j] * (i - 1LL)) % mod;
            }
            f[0] = 0;
        }
        return f[k];
    }
};