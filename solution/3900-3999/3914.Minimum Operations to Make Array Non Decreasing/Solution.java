class Solution {
    public long minOperations(int[] nums) {
        long ans = 0;
        for (int i = 1; i < nums.length; ++i) {
            ans += Math.max(nums[i - 1] - nums[i], 0);
        }
        return ans;
    }
}
