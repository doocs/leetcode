class Solution {
    public long maximumTotalCost(int[] nums) {
        long f = Long.MIN_VALUE / 2, g = 0;
        for (int x : nums) {
            long ff = Math.max(f, g) + x;
            long gg = f - x;
            f = ff;
            g = gg;
        }
        return Math.max(f, g);
    }
}