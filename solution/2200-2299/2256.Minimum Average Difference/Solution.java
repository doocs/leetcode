class Solution {
    public int minimumAverageDifference(int[] nums) {
        int n = nums.length;
        long pre = 0, suf = 0;
        for (int x : nums) {
            suf += x;
        }
        int ans = 0;
        long mi = Long.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            pre += nums[i];
            suf -= nums[i];
            long a = pre / (i + 1);
            long b = n - i - 1 == 0 ? 0 : suf / (n - i - 1);
            long t = Math.abs(a - b);
            if (t < mi) {
                ans = i;
                mi = t;
            }
        }
        return ans;
    }
}