class Solution {
public:
    int minimumDeleteSum(string s1, string s2) {
        int m = s1.size(), n = s2.size();
        int f[m + 1][n + 1];
        memset(f, 0, sizeof f);
        for (int i = 1; i <= m; ++i) {
            f[i][0] = f[i - 1][0] + s1[i - 1];
        }
        for (int j = 1; j <= n; ++j) {
            f[0][j] = f[0][j - 1] + s2[j - 1];
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (s1[i - 1] == s2[j - 1]) {
                    f[i][j] = f[i - 1][j - 1];
                } else {
                    f[i][j] = min(f[i - 1][j] + s1[i - 1], f[i][j - 1] + s2[j - 1]);
                }
            }
        }
        return f[m][n];
    }
};