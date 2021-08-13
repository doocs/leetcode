class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;
        int sum = 1;
        int left = 0, right = 0;
        while (right < n) {
            sum *= nums[right++];
            while (sum >= k && left < right) {
                sum /= nums[left++];
            }
            ans += right - left;
        }
        return ans;
    }
}