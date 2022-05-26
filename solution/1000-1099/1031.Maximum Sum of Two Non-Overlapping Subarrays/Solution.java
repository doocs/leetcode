class Solution {
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int n = nums.length;
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            s[i] = s[i - 1] + nums[i - 1];
        }
        int ans1 = 0, ans2 = 0, fm = 0, sm = 0;
        for (int i = 0; i < n - firstLen - secondLen + 1; i++) {
            fm = Math.max(s[i + firstLen] - s[i], fm);
            ans1 = Math.max(fm + s[i + firstLen + secondLen] - s[i + firstLen], ans1);
        }
        for (int i = 0; i < n - firstLen - secondLen + 1; i++) {
            sm = Math.max(s[i + secondLen] - s[i], sm);
            ans2 = Math.max(sm + s[i + firstLen + secondLen] - s[i + secondLen], ans2);
        }
        return Math.max(ans1, ans2);
    }
}