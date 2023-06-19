class Solution {
    public int rob(int[] nums) {
        int f = 0, g = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            int t = g;
            g = Math.max(g, f + nums[i]);
            f = t;
        }
        return g;
    }
}