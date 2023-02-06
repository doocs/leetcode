int distinctSubseqII(char* s) {
    int mod = 1e9 + 7;
    int n = strlen(s);
    int dp[26] = {0};
    for (int i = 0; i < n; i++) {
        int sum = 0;
        for (int j = 0; j < 26; j++) {
            sum = (sum + dp[j]) % mod;
        }
        dp[s[i] - 'a'] = sum + 1;
    }
    int res = 0;
    for (int i = 0; i < 26; i++) {
        res = (res + dp[i]) % mod;
    }
    return res;
}
