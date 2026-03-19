class Solution {
    public long maxScore(int[] nums1, int[] nums2, int K) {
        int n = nums1.length, m = nums2.length;
        long NEG = Long.MIN_VALUE / 4;
        long[][][] f = new long[n + 1][m + 1][K + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                Arrays.fill(f[i][j], NEG);
            }
        }
        f[0][0][0] = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= K; k++) {
                    if (i > 0) {
                        f[i][j][k] = Math.max(f[i][j][k], f[i - 1][j][k]);
                    }
                    if (j > 0) {
                        f[i][j][k] = Math.max(f[i][j][k], f[i][j - 1][k]);
                    }
                    if (i > 0 && j > 0 && k > 0) {
                        f[i][j][k] = Math.max(f[i][j][k],
                            f[i - 1][j - 1][k - 1] + (long) nums1[i - 1] * nums2[j - 1]);
                    }
                }
            }
        }
        return f[n][m][K];
    }
}
