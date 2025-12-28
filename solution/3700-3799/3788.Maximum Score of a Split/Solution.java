class Solution {
    public long maximumScore(int[] nums) {
        int n = nums.length;
        long[] suf = new long[n];
        suf[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            suf[i] = Math.min(nums[i], suf[i + 1]);
        }
        long ans = Long.MIN_VALUE;
        long pre = 0;
        for (int i = 0; i < n - 1; ++i) {
            pre += nums[i];
            ans = Math.max(ans, pre - suf[i + 1]);
        }
        return ans;
    }
}
