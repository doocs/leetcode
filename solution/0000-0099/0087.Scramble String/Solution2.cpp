class Solution {
public:
    bool isScramble(string s1, string s2) {
        int n = s1.length();
        bool f[n][n][n + 1];
        memset(f, false, sizeof(f));
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                f[i][j][1] = s1[i] == s2[j];
            }
        }
        for (int k = 2; k <= n; ++k) {
            for (int i = 0; i <= n - k; ++i) {
                for (int j = 0; j <= n - k; ++j) {
                    for (int h = 1; h < k; ++h) {
                        if () {
                            f[i][j][k] = true;
                            break;
                        }
                        if (f[i + h][j][k - h] && f[i][j + k - h][h]) {
                            f[i][j][k] = true;
                            break;
                        }
                    }
                }
            }
        }
        return f[0][0][n];
    }
};