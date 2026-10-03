class Solution {
    public long maxCoins(int[] lane1, int[] lane2) {
        int n = lane1.length;
        long[][][] f = new long[n + 1][2][3];
        for (int i = n - 1; i >= 0; --i) {
            for (int k = 0; k < 3; ++k) {
                for (int j = 0; j < 2; ++j) {
                    long x = j == 0 ? lane1[i] : lane2[i];
                    long ans = Math.max(x, f[i + 1][j][k] + x);
                    if (k > 0) {
                        ans = Math.max(ans, f[i + 1][j ^ 1][k - 1] + x);
                        ans = Math.max(ans, f[i][j ^ 1][k - 1]);
                    }
                    f[i][j][k] = ans;
                }
            }
        }
        long ans = f[0][0][2];
        for (int i = 1; i < n; ++i) {
            ans = Math.max(ans, f[i][0][2]);
        }
        return ans;
    }
}
