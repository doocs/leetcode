class Solution {
public:
    int goodBinaryStrings(int minLength, int maxLength, int oneGroup, int zeroGroup) {
        const int mod = 1e9 + 7;
        int f[maxLength + 1];
        memset(f, 0, sizeof f);
        f[0] = 1;
        for (int i = 1; i <= maxLength; ++i) {
            if (i - oneGroup >= 0) {
                f[i] = (f[i] + f[i - oneGroup]) % mod;
            }
            if (i - zeroGroup >= 0) {
                f[i] = (f[i] + f[i - zeroGroup]) % mod;
            }
        }
        int ans = 0;
        for (int i = minLength; i <= maxLength; ++i) {
            ans = (ans + f[i]) % mod;
        }
        return ans;
    }
};