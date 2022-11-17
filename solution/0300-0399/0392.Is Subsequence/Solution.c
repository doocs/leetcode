bool isSubsequence(char *s, char *t) {
    int n = strlen(s);
    int i = 0;
    for (int j = 0; j < n; j++) {
        while (t[i] && t[i] != s[j]) {
            i++;
        }
        if (!t[i]) {
            return 0;
        }
        i++;
    }
    return 1;
}
