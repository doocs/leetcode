class Solution {
    public int findMaximumLength(int[] nums) {
        int n = nums.length;
        long[] s = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        int[] f = new int[n + 1];
        int[] pre = new int[n + 2];
        for (int i = 1; i <= n; ++i) {
            pre[i] = Math.max(pre[i], pre[i - 1]);
            f[i] = f[pre[i]] + 1;
            int j = Arrays.binarySearch(s, s[i] * 2 - s[pre[i]]);
            pre[j < 0 ? -j - 1 : j] = i;
        }
        return f[n];
    }
}