class Solution {
public:
    int strangePrinter(string s) {
        int n = s.size();
        int f[n][n];
        memset(f, 0x3f, sizeof(f));
        for (int i = n - 1; ~i; --i) {
            f[i][i] = 1;
            for (int j = i + 1; j < n; ++j) {
                if (s[i] == s[j]) {
                    f[i][j] = f[i][j - 1];
                } else {
                    for (int k = i; k < j; ++k) {
                        f[i][j] = min(f[i][j], f[i][k] + f[k + 1][j]);
                    }
                }
            }
        }
        return f[0][n - 1];
    }
};