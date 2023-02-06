int countHomogenous(char* s) {
    int MOD = 1e9 + 7;
    int ans = 0;
    for (int i = 0, j = 0; s[j]; j++) {
        if (s[i] != s[j]) {
            i = j;
        }
        ans = (ans + j - i + 1) % MOD;
    }
    return ans;
}
