#define max(a, b) (((a) > (b)) ? (a) : (b))

int maxLengthBetweenEqualCharacters(char* s) {
    int d[26];
    memset(d, -1, sizeof(d));
    int ans = -1;
    for (int i = 0; s[i]; ++i) {
        int j = s[i] - 'a';
        if (d[j] == -1) {
            d[j] = i;
        } else {
            ans = max(ans, i - d[j] - 1);
        }
    }
    return ans;
}
