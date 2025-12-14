class Solution {
    public int absDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = 0;
        int n = nums.length;
        for (int i = 0; i < k; ++i) {
            ans += nums[n - i - 1] - nums[i];
        }
        return ans;
    }
}
