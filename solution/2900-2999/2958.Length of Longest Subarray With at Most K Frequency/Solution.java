class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int ans = 0;
        for (int i = 0, j = 0; i < nums.length; ++i) {
            cnt.merge(nums[i], 1, Integer::sum);
            while (cnt.get(nums[i]) > k) {
                cnt.merge(nums[j++], -1, Integer::sum);
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }
}