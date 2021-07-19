class Solution {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = 1;
        int window = 0;
        int l = 0, r = 1, n = nums.length;
        while (r < n) {
            window += (nums[r] - nums[r - 1]) * (r++ - l);
            while (window > k) {
                window -= nums[r - 1] - nums[l];
                l++;
            }
            ans = Math.max(ans, r - l);
        }
        return ans;
    }
}
