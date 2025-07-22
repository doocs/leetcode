class Solution {
    private static final int MX = 1330;
    private static final long[] f = new long[MX];
    static {
        for (int i = 1; i < MX; ++i) {
            f[i] = f[i - 1] + i;
            for (int j = 0; j < i; ++j) {
                f[i] += 2 * (i | j);
            }
        }
    }
    public int maxSizedArray(long s) {
        int l = 1, r = MX;
        while (l < r) {
            int m = (l + r + 1) >> 1;
            if (f[m - 1] * (m - 1) * m / 2 <= s) {
                l = m;
            } else {
                r = m - 1;
            }
        }
        return l;
    }
}
