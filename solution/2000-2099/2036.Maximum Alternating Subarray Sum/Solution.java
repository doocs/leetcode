class Solution {
    public long maximumAlternatingSubarraySum(int[] nums) {
        final long inf = 1L << 60;
        long ans = -inf, f = -inf, g = -inf;
        for (int x : nums) {
            long ff = Math.max(g, 0) + x;
            g = f - x;
            f = ff;
            ans = Math.max(ans, Math.max(f, g));
        }
        return ans;
    }
}