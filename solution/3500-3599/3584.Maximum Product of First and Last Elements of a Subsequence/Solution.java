class Solution {
    public long maximumProduct(int[] nums, int m) {
        long ans = Long.MIN_VALUE;
        int mx = Integer.MIN_VALUE;
        int mi = Integer.MAX_VALUE;
        for (int i = m - 1; i < nums.length; ++i) {
            int x = nums[i];
            int y = nums[i - m + 1];
            mi = Math.min(mi, y);
            mx = Math.max(mx, y);
            ans = Math.max(ans, Math.max(1L * x * mi, 1L * x * mx));
        }
        return ans;
    }
}