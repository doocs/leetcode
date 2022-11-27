class Solution {
public:
    const int mod = 1e9 + 7;

    int countPalindromes(string s) {
        int n = s.size();
        int pre[n + 2][10][10];
        int suf[n + 2][10][10];
        memset(pre, 0, sizeof pre);
        memset(suf, 0, sizeof suf);
        int t[n];
        for (int i = 0; i < n; ++i) t[i] = s[i] - '0';
        int c[10] = {0};
        for (int i = 1; i <= n; ++i) {
            int v = t[i - 1];
            for (int j = 0; j < 10; ++j) {
                for (int k = 0; k < 10; ++k) {
                    pre[i][j][k] = pre[i - 1][j][k];
                }
            }
            for (int j = 0; j < 10; ++j) {
                pre[i][j][v] += c[j];
            }
            c[v]++;
        }
        memset(c, 0, sizeof c);
        for (int i = n; i > 0; --i) {
            int v = t[i - 1];
            for (int j = 0; j < 10; ++j) {
                for (int k = 0; k < 10; ++k) {
                    suf[i][j][k] = suf[i + 1][j][k];
                }
            }
            for (int j = 0; j < 10; ++j) {
                suf[i][j][v] += c[j];
            }
            c[v]++;
        }
        long ans = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < 10; ++j) {
                for (int k = 0; k < 10; ++k) {
                    ans += 1ll * pre[i - 1][j][k] * suf[i + 1][j][k];
                    ans %= mod;
                }
            }
        }
        return ans;
    }
};