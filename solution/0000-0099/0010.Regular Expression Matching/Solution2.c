bool isMatch(char* s, char* p) {
    int m = strlen(s), n = strlen(p);
    bool f[m + 1][n + 1];
    memset(f, 0, sizeof(f));
    f[0][0] = true;

    for (int i = 0; i <= m; ++i) {
        for (int j = 1; j <= n; ++j) {
            if (p[j - 1] == '*') {
                f[i][j] = f[i][j - 2];
                if (i > 0 && (p[j - 2] == '.' || p[j - 2] == s[i - 1])) {
                    f[i][j] = f[i][j] || f[i - 1][j];
                }
            } else if (i > 0 && (p[j - 1] == '.' || p[j - 1] == s[i - 1])) {
                f[i][j] = f[i - 1][j - 1];
            }
        }
    }
    return f[m][n];
}
