class Solution {
    private int[] nums;
    private long[] s;
    private int k;

    public int maxFrequency(int[] nums, int k) {
        this.k = k;
        this.nums = nums;
        Arrays.sort(nums);
        int n = nums.length;
        s = new long[n + 1];
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + nums[i - 1];
        }
        int l = 1, r = n;
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    private boolean check(int m) {
        for (int i = m; i <= nums.length; ++i) {
            if (1L * nums[i - 1] * m - (s[i] - s[i - m]) <= k) {
                return true;
            }
        }
        return false;
    }
}