class Solution {
    public int longestNiceSubarray(int[] nums) {
        int ans = 0, mask = 0;
        for (int l = 0, r = 0; r < nums.length; ++r) {
            while ((mask & nums[r]) != 0) {
                mask ^= nums[l++];
            }
            mask |= nums[r];
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}
