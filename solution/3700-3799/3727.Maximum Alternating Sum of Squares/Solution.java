class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            nums[i] *= nums[i];
        }
        Arrays.sort(nums);
        long ans = 0;
        int m = n >> 1;
        for (int i = 0; i < n; ++i) {
            ans += i < m ? -nums[i] : nums[i];
        }
        return ans;
    }
}
