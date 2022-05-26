class Solution {
    public int minimumAverageDifference(int[] nums) {
        int n = nums.length;
        long[] s = new long[n];
        s[0] = nums[0];
        for (int i = 1; i < n; ++i) {
            s[i] = s[i - 1] + nums[i];
        }
        int ans = 0;
        long mi = Long.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            long a = s[i] / (i + 1);
            long b = i == n - 1 ? 0 : (s[n - 1] - s[i]) / (n - i - 1);
            long t = Math.abs(a - b);
            if (mi > t) {
                ans = i;
                mi = t;
            }
        }
        return ans;
    }
}