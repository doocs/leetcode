class Solution {
    public int waysToSplit(int[] nums) {
        double mod = 1e9 + 7;
        int n = nums.length;
        long[] pre = new long[n + 1];
        for (int i = 1; i < n + 1; i++) {
            pre[i] = pre[i - 1] + nums[i - 1];
        }
        double ans = 0;
        for (int i = 1; i < n - 1; i++) {
            if (pre[i] * 3 > pre[n]) {
                break;
            }
            int left = i + 1, right = n - 1;
            while (left < right) {
                int mid = (left + right + 1) >> 1;
                if (pre[mid] - pre[i] <= pre[n] - pre[mid]) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
            int midRight = left;
            left = i + 1; right = n - 1;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (pre[mid] - pre[i] >= pre[i]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            ans += (midRight - left + 1) % mod;
        }
        return (int) (ans % mod);
    }
}