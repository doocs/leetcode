class Solution {
    public int massage(int[] nums) {
        int f = 0, g = 0;
        for (int x : nums) {
            int ff = g + x;
            int gg = Math.max(f, g);
            f = ff;
            g = gg;
        }
        return Math.max(f, g);
    }
}