class Solution {
    public int smallestRangeII(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = nums[n - 1] - nums[0];
        for (int i = 1; i < n; ++i) {
            int mi = Math.min(nums[0] + k, nums[i] - k);
            int mx = Math.max(nums[i - 1] + k, nums[n - 1] - k);
            ans = Math.min(ans, mx - mi);
        }
        return ans;
    }
}