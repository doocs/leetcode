class Solution {
    public long rob(int[] nums, int[] colors) {
        int n = nums.length;
        long f = 0, g = nums[0];
        for (int i = 1; i < n; i++) {
            if (colors[i - 1] == colors[i]) {
                long gg = f + nums[i];
                f = Math.max(f, g);
                g = gg;
            } else {
                long gg = Math.max(f, g) + nums[i];
                f = Math.max(f, g);
                g = gg;
            }
        }
        return Math.max(f, g);
    }
}
