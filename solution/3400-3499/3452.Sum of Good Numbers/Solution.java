class Solution {
    public int sumOfGoodNumbers(int[] nums, int k) {
        int ans = 0;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (i >= k && nums[i] <= nums[i - k]) {
                continue;
            }
            if (i + k < n && nums[i] <= nums[i + k]) {
                continue;
            }
            ans += nums[i];
        }
        return ans;
    }
}
