int countAsterisks(char *s) {
    int ans = 0;
    int flag = 1;
    for (int i = 0; s[i]; i++) {
        if (s[i] == '|') {
            flag = !flag;
        } else if (s[i] == '*' && flag) {
            ans++;
        }
    }
    return ans;
}
