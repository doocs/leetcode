class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        long s = 1;
        int ans = 0;
        for (int i = 0, j = 0; j < nums.length; ++j) {
            s *= nums[j];
            while (i <= j && s >= k) {
                s /= nums[i++];
            }
            ans += j - i + 1;
        }
        return ans;
    }
}