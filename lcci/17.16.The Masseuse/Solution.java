class Solution {
    public int massage(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int n = nums.length;
        if (n < 2) {
            return n == 0 ? 0 : nums[0];
        }
        int a = nums[0], b = Math.max(nums[0], nums[1]);
        int res = b;
        for (int i = 2; i < n; ++i) {
            res = Math.max(a + nums[i], b);
            a = b;
            b = res;
        }
        return res;
    }
}