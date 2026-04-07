class Solution {
    public int minRemovals(int[] nums, int target) {
        int mx = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
        }
        int m = 32 - Integer.numberOfLeadingZeros(mx);
        if ((1 << m) <= target) {
            return -1;
        }

        int n = nums.length;
        int[][] f = new int[n + 1][1 << m];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(f[i], Integer.MIN_VALUE);
        }
        f[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            int x = nums[i - 1];
            for (int j = 0; j < (1 << m); j++) {
                f[i][j] = Math.max(f[i - 1][j], f[i - 1][j ^ x] + 1);
            }
        }

        if (f[n][target] < 0) {
            return -1;
        }
        return n - f[n][target];
    }
}
