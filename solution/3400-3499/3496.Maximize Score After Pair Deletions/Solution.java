class Solution {
    public int maxScore(int[] nums) {
        final int inf = 1 << 30;
        int n = nums.length;
        int s = 0, mi = inf;
        int t = inf;
        for (int i = 0; i < n; ++i) {
            s += nums[i];
            mi = Math.min(mi, nums[i]);
            if (i + 1 < n) {
                t = Math.min(t, nums[i] + nums[i + 1]);
            }
        }
        if (n % 2 == 1) {
            return s - mi;
        }
        return s - t;
    }
}