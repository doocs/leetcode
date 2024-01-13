class Solution {
    private long[] s;
    private int[] nums;
    private int n;
    private int k;

    public int maxFrequency(int[] nums, int k) {
        n = nums.length;
        Arrays.sort(nums);
        this.nums = nums;
        this.s = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        this.k = k;
        int left = 1, right = n;
        while (left < right) {
            int mid = (left + right + 1) >>> 1;
            if (check(mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean check(int cnt) {
        for (int i = 0; i < n + 1 - cnt; ++i) {
            int j = i + cnt - 1;
            if (1L * nums[j] * cnt - (s[j + 1] - s[i]) <= k) {
                return true;
            }
        }
        return false;
    }
}