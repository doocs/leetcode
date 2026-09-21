class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] ans = new long[k];
        long[] f = new long[k];
        for (int x : nums) {
            long[] g = new long[k];
            for (int r = 0; r < k; ++r) {
                g[(int) (1L * r * x % k)] += f[r];
            }
            g[x % k] += 1;
            for (int r = 0; r < k; ++r) {
                ans[r] += g[r];
            }
            f = g;
        }
        return ans;
    }
}
