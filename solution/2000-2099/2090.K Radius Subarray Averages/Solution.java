class Solution {
    public int[] getAverages(int[] nums, int k) {
        int n = nums.length;
        long[] s = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for (int i = 0; i < n; ++i) {
            if (i - k >= 0 && i + k < n) {
                ans[i] = (int) ((s[i + k + 1] - s[i - k]) / (k << 1 | 1));
            }
        }
        return ans;
    }
}