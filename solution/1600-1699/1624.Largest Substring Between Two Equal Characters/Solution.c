#define max(a, b) (((a) > (b)) ? (a) : (b))

int maxLengthBetweenEqualCharacters(char* s) {
    int pos[26];
    memset(pos, -1, sizeof(pos));
    int n = strlen(s);
    int res = -1;
    for (int i = 0; i < n; i++) {
        char c = s[i];
        int j = c - 'a';
        if (pos[j] == -1) {
            pos[j] = i;
        } else {
            res = max(res, i - pos[j] - 1);
        }
    }
    return res;
}
