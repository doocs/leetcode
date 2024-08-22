class Solution {
    public int longestSubarray(int[] nums) {
        int ans = 0, cnt = 0, l = 0;
        for (int x : nums) {
            cnt += x ^ 1;
            if (cnt > 1) {
                cnt -= nums[l++] ^ 1;
            }
        }
        return nums.length - l - 1;
    }
}