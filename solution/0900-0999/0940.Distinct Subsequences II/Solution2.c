int distinctSubseqII(char* s) {
    const int mod = 1e9 + 7;
    int f[26] = {0};
    int ans = 0;
    for (int i = 0; s[i]; ++i) {
        int j = s[i] - 'a';
        int add = (ans + 1LL + mod - f[j]) % mod;
        ans = (ans + add) % mod;
        f[j] = (f[j] + add) % mod;
    }
    return ans;
}
