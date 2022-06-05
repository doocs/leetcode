class Solution {
    public int minMaxGame(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int[] t = new int[n >> 1];
        for (int i = 0; i < t.length; ++i) {
            int a = nums[i << 1], b = nums[i << 1 | 1];
            t[i] = (i & 1) == 1 ? Math.max(a, b) : Math.min(a, b);
        }
        return minMaxGame(t);
    }
}