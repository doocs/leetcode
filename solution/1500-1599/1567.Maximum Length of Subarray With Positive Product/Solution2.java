class Solution {
    public int getMaxLen(int[] nums) {
        int n = nums.length;
        int f = nums[0] > 0 ? 1 : 0;
        int g = nums[0] < 0 ? 1 : 0;
        int ans = f;

        for (int i = 1; i < n; i++) {
            int ff = 0, gg = 0;
            if (nums[i] > 0) {
                ff = f + 1;
                gg = g == 0 ? 0 : g + 1;
            } else if (nums[i] < 0) {
                ff = g == 0 ? 0 : g + 1;
                gg = f + 1;
            }
            f = ff;
            g = gg;
            ans = Math.max(ans, f);
        }

        return ans;
    }
}
