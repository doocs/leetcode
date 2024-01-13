int waysToStep(int n) {
    const int mod = 1e9 + 7;
    int a = 1, b = 2, c = 4;
    for (int i = 1; i < n; ++i) {
        int t = a;
        a = b;
        b = c;
        c = (((a + b) % mod) + t) % mod;
    }
    return a;
}