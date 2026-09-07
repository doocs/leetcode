int distinctSubseqII(char* s) {
    const int mod = 1e9 + 7;
    int f[26] = {0};
    for (int i = 0; s[i]; ++i) {
        int x = 1;
        for (int j = 0; j < 26; ++j) {
            x = (x + f[j]) % mod;
        }
        f[s[i] - 'a'] = x;
    }
    int ans = 0;
    for (int i = 0; i < 26; ++i) {
        ans = (ans + f[i]) % mod;
    }
    return ans;
}
