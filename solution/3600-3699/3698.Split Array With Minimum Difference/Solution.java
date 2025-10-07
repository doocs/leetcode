class Solution {
    public long splitArray(int[] nums) {
        int n = nums.length;
        long[] s = new long[n];
        s[0] = nums[0];
        boolean[] f = new boolean[n];
        Arrays.fill(f, true);
        boolean[] g = new boolean[n];
        Arrays.fill(g, true);
        for (int i = 1; i < n; ++i) {
            s[i] = s[i - 1] + nums[i];
            f[i] = f[i - 1];
            if (nums[i] <= nums[i - 1]) {
                f[i] = false;
            }
        }
        for (int i = n - 2; i >= 0; --i) {
            g[i] = g[i + 1];
            if (nums[i] <= nums[i + 1]) {
                g[i] = false;
            }
        }
        final long inf = Long.MAX_VALUE;
        long ans = inf;
        for (int i = 0; i < n - 1; ++i) {
            if (f[i] && g[i + 1]) {
                long s1 = s[i];
                long s2 = s[n - 1] - s[i];
                ans = Math.min(ans, Math.abs(s1 - s2));
            }
        }
        return ans < inf ? ans : -1;
    }
}
