int minimumMoves(char* s) {
    int n = strlen(s);
    int ans = 0;
    int i = 0;
    while (i < n) {
        if (s[i] == 'X') {
            ans++;
            i += 3;
        } else {
            i++;
        }
    }
    return ans;
}
