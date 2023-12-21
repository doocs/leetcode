class Solution {
    public int[] distinctNumbers(int[] nums, int k) {
        int m = 0;
        for (int x : nums) {
            m = Math.max(m, x);
        }
        int[] cnt = new int[m + 1];
        int v = 0;
        for (int i = 0; i < k; ++i) {
            if (++cnt[nums[i]] == 1) {
                ++v;
            }
        }
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        ans[0] = v;
        for (int i = k; i < n; ++i) {
            if (++cnt[nums[i]] == 1) {
                ++v;
            }
            if (--cnt[nums[i - k]] == 0) {
                --v;
            }
            ans[i - k + 1] = v;
        }
        return ans;
    }
}