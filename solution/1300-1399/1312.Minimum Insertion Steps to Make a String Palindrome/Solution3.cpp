class Solution {
public:
    int minInsertions(string s) {
        int n = s.size();
        int f[n][n];
        memset(f, 0, sizeof(f));
        for (int k = 2; k <= n; ++k) {
            for (int i = 0; i + k - 1 < n; ++i) {
                int j = i + k - 1;
                if (s[i] == s[j]) {
                    f[i][j] = f[i + 1][j - 1];
                } else {
                    f[i][j] = min(f[i + 1][j], f[i][j - 1]) + 1;
                }
            }
        }
        return f[0][n - 1];
    }
};