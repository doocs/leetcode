#define min(a, b) (((a) < (b)) ? (a) : (b))

int minOperations(char* s) {
    int n = strlen(s);
    int count = 0;
    for (int i = 0; i < n; i++) {
        count += s[i] != ('0' + (i & 1)) ? 0 : 1;
    }
    return min(count, n - count);
}
