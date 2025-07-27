class Solution {
    public long maximumMedianSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        long ans = 0;
        for (int i = n / 3; i < n; i += 2) {
            ans += nums[i];
        }
        return ans;
    }
}