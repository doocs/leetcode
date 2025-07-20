char* longestPalindrome(char* s) {
    int n = strlen(s);
    bool** f = (bool**) malloc(n * sizeof(bool*));
    for (int i = 0; i < n; ++i) {
        f[i] = (bool*) malloc(n * sizeof(bool));
        for (int j = 0; j < n; ++j) {
            f[i][j] = true;
        }
    }
    int k = 0, mx = 1;
    for (int i = n - 2; ~i; --i) {
        for (int j = i + 1; j < n; ++j) {
            f[i][j] = false;
            if (s[i] == s[j]) {
                f[i][j] = f[i + 1][j - 1];
                if (f[i][j] && mx < j - i + 1) {
                    mx = j - i + 1;
                    k = i;
                }
            }
        }
    }
    char* res = (char*) malloc((mx + 1) * sizeof(char));
    strncpy(res, s + k, mx);
    res[mx] = '\0';
    for (int i = 0; i < n; ++i) {
        free(f[i]);
    }
    free(f);
    return res;
}
