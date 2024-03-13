class Solution {
    public long maximumStrength(int[] nums, int k) {
        int n = nums.length;
        long[][][] f = new long[n + 1][k + 1][2];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                Arrays.fill(f[i][j], Long.MIN_VALUE / 2);
            }
        }
        f[0][0][0] = 0;
        for (int i = 1; i <= n; i++) {
            int x = nums[i - 1];
            for (int j = 0; j <= k; j++) {
                long sign = (j & 1) == 1 ? 1 : -1;
                long val = sign * x * (k - j + 1);
                f[i][j][0] = Math.max(f[i - 1][j][0], f[i - 1][j][1]);
                f[i][j][1] = Math.max(f[i][j][1], f[i - 1][j][1] + val);
                if (j > 0) {
                    long t = Math.max(f[i - 1][j - 1][0], f[i - 1][j - 1][1]) + val;
                    f[i][j][1] = Math.max(f[i][j][1], t);
                }
            }
        }
        return Math.max(f[n][k][0], f[n][k][1]);
    }
}
