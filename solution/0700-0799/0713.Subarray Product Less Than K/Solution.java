class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int ans = 0, l = 0;
        int p = 1;
        for (int r = 0; r < nums.length; ++r) {
            p *= nums[r];
            while (l <= r && p >= k) {
                p /= nums[l++];
            }
            ans += r - l + 1;
        }
        return ans;
    }
}
