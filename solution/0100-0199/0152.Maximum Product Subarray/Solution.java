class Solution {
    public int maxProduct(int[] nums) {
        int maxf = nums[0], minf = nums[0];
        int res = nums[0], n = nums.length;
        for (int i = 1; i < n; ++i) {
            int p = maxf, q = minf;
            maxf = Math.max(nums[i], Math.max(p * nums[i], q * nums[i]));
            minf = Math.min(nums[i], Math.min(p * nums[i], q * nums[i]));
            res = Math.max(res, maxf);
        }
        return res;
    }
}