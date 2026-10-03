class Solution {
    public long maximumTotalCost(int[] nums) {
        int n = nums.length;
        long[][] f = new long[n + 1][2];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = 0; j < 2; ++j) {
                f[i][j] = nums[i] + f[i + 1][1];
                if (j == 1) {
                    f[i][j] = Math.max(f[i][j], -nums[i] + f[i + 1][0]);
                }
            }
        }
        return f[0][0];
    }
}
