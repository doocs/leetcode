int minOperations(char* s) {
    int cnt = 0;
    int n = strlen(s);
    for (int i = 0; i < n; ++i) {
        if (s[i] != "01"[i & 1]) {
            ++cnt;
        }
    }
    return cnt < (n - cnt) ? cnt : (n - cnt);
}
