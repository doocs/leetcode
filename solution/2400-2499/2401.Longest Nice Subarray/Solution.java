class Solution {
    public int longestNiceSubarray(int[] nums) {
        int j = 0, t = 0;
        int ans = 0;
        for (int i = 0; i < nums.length; ++i) {
            while ((t & nums[i]) != 0) {
                t ^= nums[j++];
            }
            t |= nums[i];
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }
}