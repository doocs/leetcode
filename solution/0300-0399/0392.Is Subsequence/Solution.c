bool isSubsequence(char * s, char * t){
    int m = strlen(s);
    int n = strlen(t);
    int i = 0;
    for (int j = 0; i < m && j < n; ++j) {
        if (s[i] == t[j]) {
            ++i;
        }
    }
    return i == m;
}