class Solution {
    public boolean canPartition(int[] nums) {
        // int s = Arrays.stream(nums).sum();
        int s = 0;
        for (int x : nums) {
            s += x;
        }
        if (s % 2 == 1) {
            return false;
        }
        int n = nums.length;
        int m = s >> 1;
        boolean[][] f = new boolean[n + 1][m + 1];
        f[0][0] = true;
        for (int i = 1; i <= n; ++i) {
            int x = nums[i - 1];
            for (int j = 0; j <= m; ++j) {
                f[i][j] = f[i - 1][j] || (j >= x && f[i - 1][j - x]);
            }
        }
        return f[n][m];
    }
}