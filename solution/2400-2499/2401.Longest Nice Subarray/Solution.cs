public class Solution {
    public int LongestNiceSubarray(int[] nums) {
        int ans = 0, mask = 0;
        for (int i = 0, j = 0; i < nums.Length; ++i) {
            while ((mask & nums[i]) != 0) {
                mask ^= nums[j++];
            }
            ans = Math.Max(ans, i - j + 1);
            mask |= nums[i];
        }
        return ans;
    }
}