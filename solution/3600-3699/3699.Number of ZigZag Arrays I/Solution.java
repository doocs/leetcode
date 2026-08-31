class Solution {
    public int zigZagArrays(int n, int l, int r) {
        final int mod = (int) 1e9 + 7;
        int m = r - l + 1;
        long[] up = new long[m];
        long[] down = new long[m];
        Arrays.fill(up, 1);
        Arrays.fill(down, 1);
        for (int k = 1; k < n; ++k) {
            long[] pre = new long[m + 1];
            long[] suf = new long[m + 1];
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
        long ans = 0;
        for (int i = 0; i < m; ++i) {
            ans = (ans + up[i] + down[i]) % mod;
        }
        return (int) ans;
    }
}
