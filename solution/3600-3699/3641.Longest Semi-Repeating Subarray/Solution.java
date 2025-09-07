class Solution {
    public int longestSubarray(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int ans = 0, cur = 0, l = 0;
        for (int r = 0; r < nums.length; ++r) {
            if (cnt.merge(nums[r], 1, Integer::sum) == 2) {
                ++cur;
            }
            while (cur > k) {
                if (cnt.merge(nums[l++], -1, Integer::sum) == 1) {
                    --cur;
                }
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}