class Solution {
    public long maximumAlternatingSubarraySum(int[] nums) {
        long ans = nums[0];
        long a = nums[0], b = -(1 << 30);
        for (int i = 1; i < nums.length; ++i) {
            long c = a, d = b;
            a = Math.max(nums[i], d + nums[i]);
            b = c - nums[i];
            ans = Math.max(ans, Math.max(a, b));
        }
        return ans;
    }
}