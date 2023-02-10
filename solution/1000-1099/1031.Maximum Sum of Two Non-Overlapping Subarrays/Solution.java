class Solution {
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int n = nums.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        int ans = 0;
        for (int i = firstLen, t = 0; i + secondLen - 1 < n; ++i) {
            t = Math.max(t, s[i] - s[i - firstLen]);
            ans = Math.max(ans, t + s[i + secondLen] - s[i]);
        }
        for (int i = secondLen, t = 0; i + firstLen - 1 < n; ++i) {
            t = Math.max(t, s[i] - s[i - secondLen]);
            ans = Math.max(ans, t + s[i + firstLen] - s[i]);
        }
        return ans;
    }
}