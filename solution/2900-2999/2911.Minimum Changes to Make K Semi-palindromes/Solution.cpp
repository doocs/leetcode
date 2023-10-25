class Solution {
public:
    int minimumChanges(string s, int k) {
        int n = s.size();
        int g[n + 1][n + 1];
        int f[n + 1][k + 1];
        memset(g, 0x3f, sizeof(g));
        memset(f, 0x3f, sizeof(f));
        f[0][0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = i; j <= n; ++j) {
                int m = j - i + 1;
                for (int d = 1; d < m; ++d) {
                    if (m % d == 0) {
                        int cnt = 0;
                        for (int l = 0; l < m; ++l) {
                            int r = (m / d - 1 - l / d) * d + l % d;
                            if (l >= r) {
                                break;
                            }
                            if (s[i - 1 + l] != s[i - 1 + r]) {
                                ++cnt;
                            }
                        }
                        g[i][j] = min(g[i][j], cnt);
                    }
                }
            }
        }
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= k; ++j) {
                for (int h = 0; h < i - 1; ++h) {
                    f[i][j] = min(f[i][j], f[h][j - 1] + g[h + 1][i]);
                }
            }
        }
        return f[n][k];
    }
};