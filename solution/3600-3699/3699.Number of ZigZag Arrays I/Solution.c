int zigZagArrays(int n, int l, int r) {
    int mod = 1e9 + 7;
    int m = r - l + 1;
    int up[m], down[m];
    for (int i = 0; i < m; ++i) {
        up[i] = down[i] = 1;
    }
    for (int k = 1; k < n; ++k) {
        int pre[m + 1], suf[m + 1];
        memset(pre, 0, sizeof(pre));
        memset(suf, 0, sizeof(suf));
        for (int i = 0; i < m; ++i) {
            pre[i + 1] = (pre[i] + down[i]) % mod;
        }
        for (int i = m - 1; i >= 0; --i) {
            suf[i] = (suf[i + 1] + up[i]) % mod;
        }
        for (int i = 0; i < m; ++i) {
            up[i] = pre[i];
            down[i] = suf[i + 1];
        }
    }
    int ans = 0;
    for (int i = 0; i < m; ++i) {
        ans = (ans + up[i] + down[i]) % mod;
    }
    return ans;
}
