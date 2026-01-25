class Solution {
    private static final int MX = 50;
    private static final long[][] c = new long[MX][MX + 1];

    static {
        for (int i = 0; i < MX; i++) {
            c[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
            }
        }
    }

    public long nthSmallest(long n, int k) {
        long ans = 0;
        for (int i = 49; i >= 0; i--) {
            if (n > c[i][k]) {
                n -= c[i][k];
                ans |= 1L << i;
                k--;
                if (k == 0) {
                    break;
                }
            }
        }
        return ans;
    }
}
