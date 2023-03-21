class Solution {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = 1, window = 0;
        for (int l = 0, r = 1; r < n; ++r) {
            window += (nums[r] - nums[r - 1]) * (r - l);
            while (window > k) {
                window -= (nums[r] - nums[l++]);
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}