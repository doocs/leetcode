class Solution {
public:
    int numPermsDISequence(string s) {
        const int mod = 1e9 + 7;
        int n = s.size();
        int f[n + 1][n + 1];
        memset(f, 0, sizeof(f));
        f[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            int pre = 0;
            if (s[i - 1] == 'D') {
                for (int j = i; j >= 0; --j) {
                    pre = (pre + f[i - 1][j]) % mod;
                    f[i][j] = pre;
                }
            } else {
                for (int j = 0; j <= i; ++j) {
                    f[i][j] = pre;
                    pre = (pre + f[i - 1][j]) % mod;
                }
            }
        }
        int ans = 0;
        for (int j = 0; j <= n; ++j) {
            ans = (ans + f[n][j]) % mod;
        }
        return ans;
    }
};