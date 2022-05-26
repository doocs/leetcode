class Solution {
    public int maxSubArray(int[] nums) {
        int res = nums[0], t = nums[0];
        for (int i = 1, n = nums.length; i < n; ++i) {
            t = nums[i] + (t < 0 ? 0 : t);
            res = Math.max(res, t);
        }
        return res;
    }
}