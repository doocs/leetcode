class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        int ans = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int l = 0, r = 0; r < nums.length; ++r) {
            cnt.merge(nums[r], 1, Integer::sum);
            while (cnt.get(nums[r]) > k) {
                cnt.merge(nums[l++], -1, Integer::sum);
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}