class Solution {
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        int ans = 1;
        int[] f = new int[n];
        int[] g = new int[n];
        f[0] = 1;
        g[0] = 1;
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i]) {
                    f[i] = Math.max(f[i], g[j] + 1);
                } else if (nums[j] > nums[i]) {
                    g[i] = Math.max(g[i], f[j] + 1);
                }
            }
            ans = Math.max(ans, Math.max(f[i], g[i]));
        }
        return ans;
    }
}