class Solution {
    public int zigZagArrays(int n, int l, int r) {
        final int mod = (int) 1e9 + 7;
        int m = r - l + 1;
        long[] up = new long[m];
        long[] dn = new long[m];
        Arrays.fill(up, 1L);
        Arrays.fill(dn, 1L);
        for (int step = 2; step <= n; ++step) {
            long[] suf = new long[m + 1];
            for (int i = m - 1; i >= 0; --i) {
                suf[i] = (suf[i + 1] + up[i]) % mod;
            }
            long[] pre = new long[m + 1];
            for (int i = 0; i < m; ++i) {
                pre[i + 1] = (pre[i] + dn[i]) % mod;
            }
            long[] nxtUp = new long[m];
            long[] nxtDn = new long[m];
            for (int x = 0; x < m; ++x) {
                nxtDn[x] = suf[x + 1];
                nxtUp[x] = pre[x];
            }
            up = nxtUp;
            dn = nxtDn;
        }
        long ans = 0;
        for (int x = 0; x < m; ++x) {
            ans = (ans + up[x] + dn[x]) % mod;
        }
        return (int) ans;
    }
}
