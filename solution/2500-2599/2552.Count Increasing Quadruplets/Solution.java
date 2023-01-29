class Solution {
    public long countQuadruplets(int[] nums) {
        int n = nums.length;
        int[][] f = new int[n][n];
        int[][] g = new int[n][n];
        for (int j = 1; j < n - 2; ++j) {
            int cnt = 0;
            for (int l = j + 1; l < n; ++l) {
                if (nums[l] > nums[j]) {
                    ++cnt;
                }
            }
            for (int k = j + 1; k < n - 1; ++k) {
                if (nums[j] > nums[k]) {
                    f[j][k] = cnt;
                } else {
                    --cnt;
                }
            }
        }
        long ans = 0;
        for (int k = 2; k < n - 1; ++k) {
            int cnt = 0;
            for (int i = 0; i < k; ++i) {
                if (nums[i] < nums[k]) {
                    ++cnt;
                }
            }
            for (int j = k - 1; j > 0; --j) {
                if (nums[j] > nums[k]) {
                    g[j][k] = cnt;
                    ans += (long) f[j][k] * g[j][k];
                } else {
                    --cnt;
                }
            }
        }
        return ans;
    }
}