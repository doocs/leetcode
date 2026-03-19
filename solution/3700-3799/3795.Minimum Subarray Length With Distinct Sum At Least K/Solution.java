class Solution {
    public int minLength(int[] nums, int k) {
        int n = nums.length;
        int ans = n + 1;
        Map<Integer, Integer> cnt = new HashMap<>();
        int l = 0;
        long s = 0;
        for (int r = 0; r < n; ++r) {
            if (cnt.merge(nums[r], 1, Integer::sum) == 1) {
                s += nums[r];
            }
            while (s >= k) {
                ans = Math.min(ans, r - l + 1);
                if (cnt.merge(nums[l], -1, Integer::sum) == 0) {
                    s -= nums[l];
                }
                ++l;
            }
        }
        return ans > n ? -1 : ans;
    }
}
