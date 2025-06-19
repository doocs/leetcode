bool isMatch(char* s, char* p) {
    int m = strlen(s), n = strlen(p);
    bool** dp = malloc((m + 1) * sizeof(bool*));
    for (int i = 0; i <= m; i++)
        dp[i] = calloc(n + 1, sizeof(bool));
    dp[0][0] = 1;
    for (int j = 2; j <= n; j++)
        if (p[j - 1] == '*')
            dp[0][j] = dp[0][j - 2];

    for (int i = 1; i <= m; i++)
        for (int j = 1; j <= n; j++)
            if (p[j - 1] == '*')
                dp[i][j] = dp[i][j - 2] || ((p[j - 2] == '.' || p[j - 2] == s[i - 1]) && dp[i - 1][j]);
            else
                dp[i][j] = (p[j - 1] == '.' || p[j - 1] == s[i - 1]) && dp[i - 1][j - 1];

    bool res = dp[m][n];
    for (int i = 0; i <= m; i++)
        free(dp[i]);
    free(dp);
    return res;
}