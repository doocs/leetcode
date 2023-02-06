#define max(a, b) (((a) > (b)) ? (a) : (b))

int longestContinuousSubstring(char* s) {
    int n = strlen(s);
    int i = 0;
    int res = 1;
    for (int j = 1; j < n; j++) {
        if (s[j] - s[j - 1] != 1) {
            res = max(res, j - i);
            i = j;
        }
    }
    return max(res, n - i);
}
