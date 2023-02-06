int countAsterisks(char* s) {
    int ans = 0;
    int ok = 1;
    for (int i = 0; s[i]; i++) {
        if (s[i] == '*') {
            ans += ok;
        } else if (s[i] == '|') {
            ok ^= 1;
        }
    }
    return ans;
}