class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        int[][] pre = new int[n][0];
        int[][] suf = new int[n][0];
        for (int i = 0, t = 0, idx = 0; i < n - k + 1; ++i) {
            int cur = s[i + k] - s[i];
            if (cur > t) {
                pre[i + k - 1] = new int[] {cur, i};
                t = cur;
                idx = i;
            } else {
                pre[i + k - 1] = new int[] {t, idx};
            }
        }
        for (int i = n - k, t = 0, idx = 0; i >= 0; --i) {
            int cur = s[i + k] - s[i];
            if (cur >= t) {
                suf[i] = new int[] {cur, i};
                t = cur;
                idx = i;
            } else {
                suf[i] = new int[] {t, idx};
            }
        }
        int[] ans = new int[0];
        for (int i = k, t = 0; i < n - 2 * k + 1; ++i) {
            int cur = s[i + k] - s[i] + pre[i - 1][0] + suf[i + k][0];
            if (cur > t) {
                ans = new int[] {pre[i - 1][1], i, suf[i + k][1]};
                t = cur;
            }
        }
        return ans;
    }
}