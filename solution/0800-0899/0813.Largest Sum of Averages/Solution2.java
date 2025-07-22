class Solution {
    public double largestSumOfAverages(int[] nums, int k) {
        int n = nums.length;
        double[][] f = new double[n + 1][k + 1];
        int[] s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        for (int i = 1; i <= n; ++i) {
            f[i][1] = s[i] * 1.0 / i;
            for (int j = 2; j <= Math.min(i, k); ++j) {
                for (int h = 0; h < i; ++h) {
                    f[i][j] = Math.max(f[i][j], f[h][j - 1] + (s[i] - s[h]) * 1.0 / (i - h));
                }
            }
        }
        return f[n][k];
    }
}
