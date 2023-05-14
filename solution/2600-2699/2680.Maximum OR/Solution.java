class Solution {
    public long maximumOr(int[] nums, int k) {
        int n = nums.length;
        long[] suf = new long[n + 1];
        for (int i = n - 1; i >= 0; --i) {
            suf[i] = suf[i + 1] | nums[i];
        }
        long ans = 0, pre = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, pre | (1L * nums[i] << k) | suf[i + 1]);
            pre |= nums[i];
        }
        return ans;
    }
}