class Solution {
    public long maximumSumScore(int[] nums) {
        int n = nums.length;
        long[] s = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        long ans = Long.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, Math.max(s[i + 1], s[n] - s[i]));
        }
        return ans;
    }
}