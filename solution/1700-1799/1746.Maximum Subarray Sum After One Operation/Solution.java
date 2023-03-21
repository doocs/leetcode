class Solution {
    public int maxSumAfterOperation(int[] nums) {
        int f = 0, g = 0;
        int ans = Integer.MIN_VALUE;
        for (int x : nums) {
            int ff = Math.max(f, 0) + x;
            int gg = Math.max(Math.max(f, 0) + x * x, g + x);
            f = ff;
            g = gg;
            ans = Math.max(ans, Math.max(f, g));
        }
        return ans;
    }
}