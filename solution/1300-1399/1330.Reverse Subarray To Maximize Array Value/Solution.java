class Solution {
    public int maxValueAfterReverse(int[] nums) {
        int n = nums.length;
        int s = 0;
        for (int i = 0; i < n - 1; ++i) {
            s += Math.abs(nums[i] - nums[i + 1]);
        }
        int ans = s;
        for (int i = 0; i < n - 1; ++i) {
            ans = Math.max(
                ans, s + Math.abs(nums[0] - nums[i + 1]) - Math.abs(nums[i] - nums[i + 1]));
            ans = Math.max(
                ans, s + Math.abs(nums[n - 1] - nums[i]) - Math.abs(nums[i] - nums[i + 1]));
        }
        int[] dirs = {1, -1, -1, 1, 1};
        final int inf = 1 << 30;
        for (int k = 0; k < 4; ++k) {
            int k1 = dirs[k], k2 = dirs[k + 1];
            int mx = -inf, mi = inf;
            for (int i = 0; i < n - 1; ++i) {
                int a = k1 * nums[i] + k2 * nums[i + 1];
                int b = Math.abs(nums[i] - nums[i + 1]);
                mx = Math.max(mx, a - b);
                mi = Math.min(mi, a + b);
            }
            ans = Math.max(ans, s + Math.max(0, mx - mi));
        }
        return ans;
    }
}