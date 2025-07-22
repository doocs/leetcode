#define max(a, b) (((a) > (b)) ? (a) : (b))

int integerBreak(int n) {
    int* f = (int*) malloc((n + 1) * sizeof(int));
    f[1] = 1;
    for (int i = 2; i <= n; ++i) {
        f[i] = 0;
        for (int j = 1; j < i; ++j) {
            f[i] = max(f[i], max(f[i - j] * j, (i - j) * j));
        }
    }
    return f[n];
}